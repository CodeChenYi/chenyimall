package com.chenyi.mall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.api.cart.feign.CartFeignService;
import com.chenyi.mall.api.cart.to.CartItemTO;
import com.chenyi.mall.api.coupon.feign.CouponFeignService;
import com.chenyi.mall.api.member.feign.MemberFeignService;
import com.chenyi.mall.api.member.to.MemberInfo;
import com.chenyi.mall.api.member.to.MemberReceiveAddressTO;
import com.chenyi.mall.api.order.to.LockOrderItemTO;
import com.chenyi.mall.api.order.to.OrderTO;
import com.chenyi.mall.api.ware.feign.WareFeignService;
import com.chenyi.mall.common.constant.ChenYiMallConstant;
import com.chenyi.mall.common.constant.RabbitConstant;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.exception.ChenYiMallException;
import com.chenyi.mall.common.utils.*;
import com.chenyi.mall.order.dto.OrderDTO;
import com.chenyi.mall.order.entity.OrderEntity;
import com.chenyi.mall.order.entity.OrderItemEntity;
import com.chenyi.mall.order.interceptor.OrderInterceptor;
import com.chenyi.mall.order.mapper.OrderMapper;
import com.chenyi.mall.order.service.OrderItemService;
import com.chenyi.mall.order.service.OrderService;
import com.chenyi.mall.order.vo.OrderBackInfoVO;
import com.chenyi.mall.order.vo.OrderConfirmVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    @Resource
    private CartFeignService cartFeignService;

    @Resource
    private MemberFeignService memberFeignService;

    @Resource
    private CouponFeignService couponFeignService;

    @Resource
    private WareFeignService wareFeignService;

    @Resource
    private ThreadPoolExecutor executor;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private OrderItemService orderItemService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public OrderConfirmVO getOrderConfirm() {
        OrderConfirmVO confirmVO = new OrderConfirmVO();

        // 获取当前请求
        ServletRequestAttributes servletRequestAttributes = ServletUtils.getServletRequestAttributes();
        // 查询购物车商品数量
        CompletableFuture<Void> cartItemListFuture = CompletableFuture.runAsync(() -> {
            // 共享请求头
            RequestContextHolder.setRequestAttributes(servletRequestAttributes);
            R checkCartItem = cartFeignService.getCheckCartItem();
            if (ResultEnum.SUCCESS.getCode().equals(checkCartItem.getCode())) {
                List<CartItemTO> cartItemList = checkCartItem.getList("cartItemList", CartItemTO.class);
                confirmVO.setCartItemList(cartItemList);
            }
        }, executor);

        // 查询用户地址信息
        MemberInfo memberInfo = OrderInterceptor.MEMBER_THREAD_LOCAL.get();
        CompletableFuture<Void> memberAddressFuture = CompletableFuture.runAsync(() -> {
            // 共享请求头
            RequestContextHolder.setRequestAttributes(servletRequestAttributes);
            if (memberInfo != null) {
                Long id = memberInfo.getMember().getId();
                R memberAddressInfo = memberFeignService.getInfoByMemberId(id);
                if (ResultEnum.SUCCESS.getCode().equals(memberAddressInfo.getCode())) {
                    List<MemberReceiveAddressTO> memberAddressList = memberAddressInfo.getList("memberAddressList", MemberReceiveAddressTO.class);
                    confirmVO.setMemberReceiveAddressList(memberAddressList);
                }
            }
        }, executor);

        CompletableFuture.allOf(cartItemListFuture, memberAddressFuture).join();
        return confirmVO;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public OrderBackInfoVO saveOrder(OrderDTO order) {
        Long memberId = OrderInterceptor.MEMBER_THREAD_LOCAL.get().memberId();
        log.info("==================开始下单===================");
        // 验证请求id
        String requestId = verifyRequestId();

        OrderEntity orderEntity = new OrderEntity();
        ServletRequestAttributes attributes = ServletUtils.getServletRequestAttributes();
        // 获取购物车选中信息
        CompletableFuture<R> checkCartItemFutureR = CompletableFuture.supplyAsync(() -> {
            RequestContextHolder.setRequestAttributes(attributes);
            return cartFeignService.getCheckCartItem();
        }, executor);
        // 设置订单总价信息
        CompletableFuture<List<CartItemTO>> cartItemFuture = checkCartItemFutureR.thenApplyAsync(r -> {
            if (ResultEnum.SUCCESS.getCode().equals(r.getCode())) {
                List<CartItemTO> cartItemList = r.getList("cartItemList", CartItemTO.class);
                BigDecimal totalPrice = new BigDecimal("0.00");
                for (CartItemTO cartItem : cartItemList) {
                    totalPrice = totalPrice.add(cartItem.getPrice());
                }
                orderEntity.setTotalAmount(totalPrice);
                return cartItemList;
            }
            return null;
        }, executor);

        // 设置用户优惠卷信息
        CompletableFuture<Void> couponInfoFuture = cartItemFuture.thenRunAsync(() -> {
            setCouponInfo(memberId, orderEntity, order.getCouponId());
        }, executor);

        // 设置支付与发票信息
        CompletableFuture<Void> payAndBillFuture = CompletableFuture.runAsync(() -> {
            setPayAndBill(order, orderEntity);
        }, executor);

        // 设置收货地址信息
        CompletableFuture<Void> memberAddressFuture = CompletableFuture.runAsync(() -> {
            setMemberAddressInfo(order, orderEntity);
        }, executor);

        CompletableFuture.allOf(checkCartItemFutureR,
                cartItemFuture,
                couponInfoFuture,
                payAndBillFuture,
                memberAddressFuture).join();

        // 保存订单信息
        saveOrderInfo(orderEntity, order);
        List<CartItemTO> cartItems = cartItemFuture.join();
        List<LockOrderItemTO> orderItemList = saveOrderItem(cartItems, orderEntity.getId(), orderEntity.getOrderSn());

        // 锁定库存
        CompletableFuture<R> wareFuture = CompletableFuture
                .supplyAsync(() -> wareFeignService.lockOrderWare(orderItemList), executor);

        try {
            R r = wareFuture.get(5, TimeUnit.SECONDS);
            if (!ResultEnum.SUCCESS.getCode().equals(r.getCode())) {
                throw new ChenYiMallException(ResultEnum.ORDER_CREATED_FAIL.getCode(),
                        ResultEnum.LOCK_STOCK_FAIL.getMsg());
            }
        } catch (Exception e) {
            throw new ChenYiMallException(ResultEnum.LOCK_STOCK_FAIL.getCode(),
                    ResultEnum.LOCK_STOCK_FAIL.getMsg());
        }
        // TODO 可以增加其他业务，比如会员扣减积分等

        OrderBackInfoVO orderBackInfoVO = new OrderBackInfoVO();
        BeanUtils.copyProperties(orderEntity, orderBackInfoVO);

        rabbitTemplate.convertAndSend(RabbitConstant.ORDER_EVENT_EXCHANGE,
                RabbitConstant.ORDER_CREATE_ORDER_KEY, orderEntity);

        stringRedisTemplate.delete(ChenYiMallConstant.CART_USER + memberId);
        stringRedisTemplate.delete(ChenYiMallConstant.READY_SUBMIT_ORDER + requestId);
        return orderBackInfoVO;
    }

    @Override
    public void closeOrder(OrderEntity orderEntity) {
        log.info("-------------------订单过期关闭订单-------------------");
        OrderEntity entity = baseMapper.selectById(orderEntity.getId());
        if (ChenYiMallConstant.PENDING_PAY.equals(entity.getStatus())) {
            entity.setStatus(ChenYiMallConstant.ORDER_CLOSED);
            baseMapper.updateById(entity);
            // 发送消息给库存服务，解锁订单
            log.info("----------------发送消息解锁库存--------------------");
            OrderTO orderTO = new OrderTO();
            BeanUtils.copyProperties(orderEntity, orderTO);
            rabbitTemplate.convertAndSend(RabbitConstant.ORDER_EVENT_EXCHANGE,
                    RabbitConstant.ORDER_RELEASE_OTHER_KEY, orderTO);
        }
    }

    private List<LockOrderItemTO> saveOrderItem(List<CartItemTO> cartItems, Long orderId, String orderSn) {
        // TODO 查询Spu等信息，设置属性等
        List<OrderItemEntity> orderItemList = cartItems.stream().map(cartItem -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrderId(orderId);
            orderItemEntity.setSkuId(cartItem.getSkuId());
            orderItemEntity.setSkuQuantity(cartItem.getCount());
            BeanUtils.copyProperties(cartItem, orderItemEntity);
            return orderItemEntity;
        }).collect(Collectors.toList());
        orderItemService.saveBatch(orderItemList);
        // 拷贝对象进行返回
        return orderItemList.stream().map(orderItemEntity -> {
            LockOrderItemTO orderItem = new LockOrderItemTO();
            orderItem.setOrderId(orderId);
            orderItem.setOrderSn(orderSn);
            orderItem.setSkuName(orderItemEntity.getSkuName());
            orderItem.setSkuQuantity(orderItemEntity.getSkuQuantity());
            orderItem.setSkuId(orderItemEntity.getSkuId());
            return orderItem;
        }).collect(Collectors.toList());
    }

    private static void setPayAndBill(OrderDTO order, OrderEntity orderEntity) {
        orderEntity.setSourceType(ChenYiMallConstant.PC_ORDER);
        orderEntity.setStatus(ChenYiMallConstant.PENDING_PAY);
        if (order.getBillType().equals(ChenYiMallConstant.NO_BILL)) {
            orderEntity.setBillType(ChenYiMallConstant.NO_BILL);
        } else {
            orderEntity.setBillType(order.getBillType());
        }
    }

    private void setMemberAddressInfo(OrderDTO order, OrderEntity orderEntity) {
        R memberAddressR = memberFeignService.info(order.getMemberAddressId());
        if (ResultEnum.SUCCESS.getCode().equals(memberAddressR.getCode())) {
            // 保存收货地址信息
            MemberReceiveAddressTO memberReceiveAddress = memberAddressR.get("memberReceiveAddress", MemberReceiveAddressTO.class);
            if (memberReceiveAddress != null) {
                orderEntity.setReceiverName(memberReceiveAddress.getName());
                orderEntity.setReceiverCity(memberReceiveAddress.getCity());
                orderEntity.setReceiverPhone(memberReceiveAddress.getPhone());
                orderEntity.setReceiverDetailAddress(memberReceiveAddress.getDetailAddress());
                orderEntity.setReceiverProvince(memberReceiveAddress.getProvince());
                orderEntity.setReceiverRegion(memberReceiveAddress.getRegion());
            }
        }
    }

    private void setCouponInfo(Long memberId, OrderEntity orderEntity, Long couponId) {
        if (couponId != null) {
            R couponInfoR = couponFeignService.getCouponInfoByMemberId(memberId, couponId);
            if (ResultEnum.SUCCESS.getCode().equals(couponInfoR.getCode())) {
                // TODO 获取优惠卷信息后减去优惠卷价格
                orderEntity.setPayAmount(orderEntity.getTotalAmount());
                orderEntity.setCouponId(couponId);
                // TODO 查询设置优惠卷抵扣的金额
                orderEntity.setCouponAmount(new BigDecimal("0.00"));
            }
        }
    }

    private int saveOrderInfo(OrderEntity orderEntity, OrderDTO order) {
        orderEntity.setOrderSn(ChenYiMallUtils.getOrderNo());
        orderEntity.setMemberId(order.getMemberId());
        orderEntity.setCommentTime(new Date());
        orderEntity.setMemberUsername(order.getMemberUsername());
        return baseMapper.insert(orderEntity);
    }

    private String verifyRequestId() {
        String requestId = ServletUtils.getHeader(ChenYiMallConstant.REQUEST_ID);
        log.info("request_id {}", requestId);
        // 没有带上requestId
        if (requestId == null) {
            throw new ChenYiMallException(ResultEnum.ILLEGAL_ORDER_REQUEST.getCode(),
                    ResultEnum.ILLEGAL_ORDER_REQUEST.getMsg());
        }
        String saveRequestId = stringRedisTemplate
                .opsForValue()
                .get(ChenYiMallConstant.READY_SUBMIT_ORDER + requestId);
        // 相同requestId订单重复提交
        if (saveRequestId != null && saveRequestId.equals(requestId)) {
            throw new ChenYiMallException(ResultEnum.DUPLICATE_CREATED_ORDER.getCode(),
                    ResultEnum.DUPLICATE_CREATED_ORDER.getMsg());
        }
        stringRedisTemplate.opsForValue()
                .set(ChenYiMallConstant.READY_SUBMIT_ORDER + requestId,
                        requestId,
                        ChenYiMallConstant.THIRTY_MINUTE_MILLIS_VALUE,
                        TimeUnit.MINUTES);
        return requestId;
    }

}
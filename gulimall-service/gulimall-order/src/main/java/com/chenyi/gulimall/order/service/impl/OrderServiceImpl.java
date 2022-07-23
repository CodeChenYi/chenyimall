package com.chenyi.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.gulimall.cart.feign.CartFeignService;
import com.chenyi.gulimall.cart.to.CartItemTO;
import com.chenyi.gulimall.common.enums.ResultEnum;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.Query;
import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.member.feign.MemberFeignService;
import com.chenyi.gulimall.member.to.MemberInfo;
import com.chenyi.gulimall.member.to.MemberReceiveAddressTO;
import com.chenyi.gulimall.order.dto.OrderDTO;
import com.chenyi.gulimall.order.entity.OrderEntity;
import com.chenyi.gulimall.order.interceptor.OrderInterceptor;
import com.chenyi.gulimall.order.mapper.OrderMapper;
import com.chenyi.gulimall.order.service.OrderService;
import com.chenyi.gulimall.order.vo.OrderConfirmVO;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    @Resource
    private CartFeignService cartFeignService;

    @Resource
    private MemberFeignService memberFeignService;

    @Resource
    private ThreadPoolExecutor executor;

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
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 查询购物车商品数量
        CompletableFuture<Void> cartItemListFuture = CompletableFuture.runAsync(() -> {
            // 共享请求头
            RequestContextHolder.setRequestAttributes(requestAttributes);
            R checkCartItem = cartFeignService.getCheckCartItem();
            if (ResultEnum.SUCCESS.getCode().equals(checkCartItem.getCode())) {
                List<CartItemTO> cartItemList = checkCartItem.getList("cartItemList", CartItemTO.class);
                confirmVO.setCartItemList(cartItemList);
            }
        }, executor);

        // 查询用户地址信息
        MemberInfo memberInfo = OrderInterceptor.threadLocal.get();
        CompletableFuture<Void> memberAddressFuture = CompletableFuture.runAsync(() -> {
            // 共享请求头
            RequestContextHolder.setRequestAttributes(requestAttributes);
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

    @Override
    public void saveOrder(OrderDTO order) {

    }

}
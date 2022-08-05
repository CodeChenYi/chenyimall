package com.chenyi.mall.ware.service.impl;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.api.order.feign.OrderFeignService;
import com.chenyi.mall.api.order.to.LockOrderItemTO;
import com.chenyi.mall.api.order.to.OrderTO;
import com.chenyi.mall.api.ware.to.StockLockTO;
import com.chenyi.mall.api.ware.to.WareDetailTO;
import com.chenyi.mall.common.constant.ChenYiMallConstant;
import com.chenyi.mall.common.constant.RabbitConstant;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.ware.entity.WareOrderTaskDetailEntity;
import com.chenyi.mall.ware.entity.WareOrderTaskEntity;
import com.chenyi.mall.ware.entity.WareSkuEntity;
import com.chenyi.mall.ware.mapper.WareSkuMapper;
import com.chenyi.mall.ware.service.WareInfoService;
import com.chenyi.mall.ware.service.WareOrderTaskDetailService;
import com.chenyi.mall.ware.service.WareOrderTaskService;
import com.chenyi.mall.ware.service.WareSkuService;
import com.chenyi.mall.api.ware.to.WareSkuTo;
import com.chenyi.mall.ware.vo.LockWareVO;
import com.chenyi.mall.ware.vo.WareSkuVO;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.exception.ChenYiMallException;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;
import com.chenyi.mall.ware.entity.WareInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSkuEntity> implements WareSkuService {

    @Resource
    private WareInfoService wareInfoService;

    @Resource
    private WareOrderTaskService wareOrderTaskService;

    @Resource
    private WareOrderTaskDetailService wareOrderTaskDetailService;

    @Resource
    private OrderFeignService orderFeignService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                wrapper
        );
        List<WareSkuEntity> records = page.getRecords();
        List<WareSkuVO> wareSkuVOList = records.stream().map(wareSku -> {
            WareSkuVO wareSkuVO = new WareSkuVO();
            BeanUtils.copyProperties(wareSku, wareSkuVO);
            Long wareId = wareSku.getWareId();
            WareInfoEntity wareInfo = wareInfoService.getById(wareId);
            if (wareInfo != null) {
                wareSkuVO.setWareName(wareInfo.getName());
            }
            return wareSkuVO;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(wareSkuVOList);
        return pageUtils;
    }

    @Override
    public List<WareSkuTo> infoBySkuId(List<String> skuId) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        wrapper.in("sku_id", skuId);
        List<WareSkuEntity> wareSkuEntities = baseMapper.selectList(wrapper);
        return wareSkuEntities.stream().map(wareSkuEntity -> {
            WareSkuTo wareSkuTo = new WareSkuTo();
            BeanUtils.copyProperties(wareSkuEntity, wareSkuTo);
            return wareSkuTo;
        }).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean lockOrderWare(List<LockOrderItemTO> orderItems) {

        // 保存库存工作单
        WareOrderTaskEntity wareOrderTaskEntity = new WareOrderTaskEntity();
        if (orderItems != null && orderItems.size() > 0) {
            LockOrderItemTO lockOrderItemTO = orderItems.get(0);
            wareOrderTaskEntity.setOrderSn(lockOrderItemTO.getOrderSn());
            wareOrderTaskEntity.setOrderId(lockOrderItemTO.getOrderId());
            wareOrderTaskService.save(wareOrderTaskEntity);
        }
        // 查找当前商品所有的库存
        log.info("orderItems {}", orderItems);
        List<LockWareVO> lockWares = orderItems.stream().map(item -> {
            log.info("quantity {}", item.getSkuQuantity());
            LockWareVO lockWareVO = new LockWareVO();
            lockWareVO.setSkuId(item.getSkuId());
            lockWareVO.setNum(item.getSkuQuantity());
            lockWareVO.setSkuName(item.getSkuName());
            List<Long> wareIds = baseMapper.selectIsItInStock(item.getSkuId(), item.getSkuQuantity());
            lockWareVO.setWareIds(wareIds);
            return lockWareVO;
        }).collect(Collectors.toList());


        for (LockWareVO lockWare : lockWares) {
            // 判断当前是否查询到库存
            if (lockWare.getWareIds() == null || lockWare.getWareIds().size() == 0) {
                throw new ChenYiMallException(ResultEnum.PRODUCT_NO_STOCK.getCode(),
                        ResultEnum.PRODUCT_NO_STOCK.getMsg() + lockWare.getSkuId());
            }
            // 锁定库存
            boolean isLockStock = false;
            List<Long> wareIds = lockWare.getWareIds();
            for (Long wareId : wareIds) {
                int count = baseMapper.lockStock(wareId, lockWare.getSkuId(), lockWare.getNum());
                if (count > 0) {
                    // 成功之后保存库存工作单详情
                    WareOrderTaskDetailEntity wareOrderTaskDetailEntity =
                            new WareOrderTaskDetailEntity(null,
                                    lockWare.getSkuId(),
                                    lockWare.getSkuName(),
                                    lockWare.getNum(),
                                    wareOrderTaskEntity.getId(),
                                    wareId,
                                    ChenYiMallConstant.WARE_ALREADY_LOCKED);
                    wareOrderTaskDetailService.save(wareOrderTaskDetailEntity);
                    // 发送消息到MQ
                    StockLockTO stockLockTO = new StockLockTO();
                    WareDetailTO wareDetailTO = new WareDetailTO();
                    BeanUtils.copyProperties(wareOrderTaskDetailEntity, wareDetailTO);
                    stockLockTO.setTaskId(wareOrderTaskEntity.getId());
                    stockLockTO.setDetail(wareDetailTO);
                    rabbitTemplate.convertAndSend(RabbitConstant.STOCK_EVENT_EXCHANGE,
                            RabbitConstant.STOCK_LOCK_KEY, stockLockTO);
                    isLockStock = true;
                    break;
                }
            }
            // 如果都没有锁成功那么就直接抛出异常
            if (!isLockStock) {
                throw new ChenYiMallException(ResultEnum.PRODUCT_NO_STOCK.getCode(),
                        ResultEnum.PRODUCT_NO_STOCK.getMsg() + lockWare.getSkuId());
            }
        }

        return true;
    }

    @Override
    public void unLockStock(StockLockTO stockLockTO) {
        log.info("==================补偿逻辑解锁库存======================");
        // 先查询是否有库存工作单，判断商品是否是库存服务出错导致的回滚
        WareDetailTO detail = stockLockTO.getDetail();
        WareOrderTaskDetailEntity byId = wareOrderTaskDetailService.getById(detail.getId());
        if (byId != null && byId.getLockStatus().equals(ChenYiMallConstant.WARE_ALREADY_LOCKED)) {
            // 解锁逻辑，先查询库存工作单后查询订单在判断是否解锁
            WareOrderTaskEntity taskById = wareOrderTaskService.getById(stockLockTO.getTaskId());
            R info = orderFeignService.info(taskById.getOrderId());
            if (info != null) {
                OrderTO order = info.get("order", OrderTO.class);
                if (order == null || ChenYiMallConstant.ORDER_CLOSED.equals(order.getStatus())) {
                    // 订单以取消必须解锁订单
                    baseMapper.unLockStock(detail.getSkuId(), detail.getWareId(),detail.getSkuNum());
                    // 更新库存详细工作单信息
                    byId.setLockStatus(ChenYiMallConstant.WARE_UNLOCKED);
                    wareOrderTaskDetailService.updateById(byId);
                }
            } else {
                throw new ChenYiMallException(10015, "远程服务调用失败");
            }
        }
    }

    @Override
    public void unLockStock(OrderTO order) {
        log.info("----------------订单过期开始解锁库存-----------------");
        Long id = order.getId();
        WareOrderTaskEntity orderTaskEntity = wareOrderTaskService
                .getOne(new QueryWrapper<WareOrderTaskEntity>().eq("order_id", id));
        List<WareOrderTaskDetailEntity> detailList = wareOrderTaskDetailService
                .list(new QueryWrapper<WareOrderTaskDetailEntity>()
                        .eq("task_id", orderTaskEntity.getId()));
        for (WareOrderTaskDetailEntity detail : detailList) {
            if (ChenYiMallConstant.WARE_ALREADY_LOCKED.equals(detail.getLockStatus())) {
                // 解锁库存
                baseMapper.unLockStock(detail.getSkuId(), detail.getWareId(), detail.getSkuNum());
                // 更新库存工作单
                detail.setLockStatus(ChenYiMallConstant.WARE_UNLOCKED);
                wareOrderTaskDetailService.updateById(detail);
            }
        }
    }

}
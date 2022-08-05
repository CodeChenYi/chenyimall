package com.chenyi.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.api.order.to.LockOrderItemTO;
import com.chenyi.mall.api.order.to.OrderTO;
import com.chenyi.mall.api.ware.to.StockLockTO;
import com.chenyi.mall.ware.entity.WareSkuEntity;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.api.ware.to.WareSkuTo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:13:30
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询商品库存信息
     * @param skuId
     * @return
     */
    List<WareSkuTo> infoBySkuId(List<String> skuId);

    /**
     * 锁定商品库存信息
     * @param orderItems
     * @return
     */
    boolean lockOrderWare(List<LockOrderItemTO> orderItems);

    /**
     * 库存补偿自动解锁解锁
     * @param stockLockTO
     */
    void unLockStock(StockLockTO stockLockTO);

    /**
     * 订单自动库存解锁
     * @param order
     */
    void unLockStock(OrderTO order);
}


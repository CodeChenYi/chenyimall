package com.chenyi.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.order.dto.OrderDTO;
import com.chenyi.mall.order.entity.OrderEntity;
import com.chenyi.mall.order.vo.OrderConfirmVO;

import java.util.Map;

/**
 * 订单
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:07:20
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取确认订单页面数据
     * @return
     */
    OrderConfirmVO getOrderConfirm();

    /**
     * 保存订单信息
     * @param order
     */
    void saveOrder(OrderDTO order);
}


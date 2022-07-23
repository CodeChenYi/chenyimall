package com.chenyi.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.order.dto.OrderDTO;
import com.chenyi.gulimall.order.entity.OrderEntity;
import com.chenyi.gulimall.order.vo.OrderConfirmVO;

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


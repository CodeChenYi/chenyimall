package com.chenyi.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.order.entity.OrderOperateHistoryEntity;

import java.util.Map;

/**
 * 订单操作历史记录
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:07:19
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


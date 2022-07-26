package com.chenyi.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.order.entity.OrderSettingEntity;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:07:20
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


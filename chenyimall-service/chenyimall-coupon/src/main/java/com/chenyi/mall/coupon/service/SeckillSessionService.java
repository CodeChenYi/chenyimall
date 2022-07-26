package com.chenyi.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.coupon.entity.SeckillSessionEntity;

import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-12-07 01:27:49
 */
public interface SeckillSessionService extends IService<SeckillSessionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


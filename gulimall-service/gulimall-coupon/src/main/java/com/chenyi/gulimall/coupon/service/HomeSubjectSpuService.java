package com.chenyi.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * 专题商品
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-12-07 01:27:49
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


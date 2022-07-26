package com.chenyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.product.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


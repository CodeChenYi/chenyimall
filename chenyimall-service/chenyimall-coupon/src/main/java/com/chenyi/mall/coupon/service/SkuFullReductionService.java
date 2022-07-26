package com.chenyi.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.coupon.entity.SkuFullReductionEntity;
import com.chenyi.mall.product.to.SkuReductionTO;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-12-07 01:27:48
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存sku信息
     * @param skuReductionTO
     */
    void saveSkuInfo(SkuReductionTO skuReductionTO);

}


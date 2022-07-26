package com.chenyi.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.ware.entity.WareSkuEntity;
import com.chenyi.mall.ware.to.WareSkuTo;

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
}


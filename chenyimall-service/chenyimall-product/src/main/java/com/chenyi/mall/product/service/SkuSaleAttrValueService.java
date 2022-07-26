package com.chenyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.product.entity.SkuSaleAttrValueEntity;
import com.chenyi.mall.product.vo.SkuItemVO;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取销售属性信息
     * @param spuId
     * @return
     */
    List<SkuItemVO.SkuSaleAttrVO> getSaleAttrValue(String spuId);

    /**
     * 根据skuId查询销售属性信息
     * @param skuId
     * @return
     */
    List<String> getSaleAttrBySkuId(String skuId);
}


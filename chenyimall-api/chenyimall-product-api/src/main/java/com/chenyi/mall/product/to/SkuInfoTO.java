package com.chenyi.mall.product.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenyi
 * @className SkuInfoTO
 * @date 2022/6/29 21:01
 */
@Data
public class SkuInfoTO {

    private String skuId;
    /**
     * spuId
     */
    private String spuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * sku介绍描述
     */
    private String skuDesc;
    /**
     * 所属分类id
     */
    private String catalogId;
    /**
     * 品牌id
     */
    private String brandId;
    /**
     * 默认图片
     */
    private String skuDefaultImg;
    /**
     * 标题
     */
    private String skuTitle;
    /**
     * 副标题
     */
    private String skuSubtitle;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 销量
     */
    private Long saleCount;

}

package com.chenyi.gulimall.product.dto;

import lombok.Data;

/**
 * Sku销售属性DTO
 * @author chenyi
 * @className SpuAttrDTO
 * @date 2022/5/10 0:16
 */
@Data
public class SkuSaleAttrDTO {
    /**
     * sku销售属性id
     */
    private String attrId;
    /**
     * sku销售属性分组名称
     */
    private String attrName;
    /**
     * sku销售属性值
     */
    private String attrValue;
}

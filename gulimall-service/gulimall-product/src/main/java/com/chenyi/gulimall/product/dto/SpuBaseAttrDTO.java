package com.chenyi.gulimall.product.dto;

import lombok.Data;

/**
 * Spu基本属性，规格参数
 * @author chenyi
 * @className BaseAttrDTO
 * @date 2022/5/10 0:13
 */
@Data
public class SpuBaseAttrDTO {

    /**
     * 规格参数id
     */
    private String attrId;
    /**
     * 规格参数值
     */
    private String attrValues;
    /**
     * 规格参数显示状态
     */
    private int showDesc;
}

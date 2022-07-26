package com.chenyi.mall.ware.dto;

import lombok.Data;

/**
 * @author chenyi
 * @className WareSkuDTO
 * @date 2022/5/13 15:38
 */
@Data
public class WareSkuDTO {

    /**
     * id
     */
    private String id;
    /**
     * sku_id
     */
    private String skuId;
    /**
     * 仓库id
     */
    private String wareId;
    /**
     * 库存数
     */
    private Integer stock;
    /**
     * sku_name
     */
    private String skuName;
    /**
     * 锁定库存
     */
    private Integer stockLocked;
}

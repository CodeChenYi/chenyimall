package com.chenyi.gulimall.ware.to;

import lombok.Data;

/**
 * @author chenyi
 * @className WareSkuTo
 * @date 2022/7/1 17:09
 */
@Data
public class WareSkuTo {
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

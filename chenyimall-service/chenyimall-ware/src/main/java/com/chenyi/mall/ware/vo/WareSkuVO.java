package com.chenyi.mall.ware.vo;

import lombok.Data;

/**
 * @author chenyi
 * @className WareSkuVO
 * @date 2022/5/13 16:10
 */
@Data
public class WareSkuVO {
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
    /**
     * 仓库名称
     */
    private String wareName;
}

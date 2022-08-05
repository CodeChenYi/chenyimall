package com.chenyi.mall.api.ware.to;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenyi
 * @className WareDetailTO
 * @date 2022/7/31 17:36
 */
@Data
public class WareDetailTO implements Serializable {
    private Long id;
    /**
     * sku_id
     */
    private Long skuId;
    /**
     * sku_name
     */
    private String skuName;
    /**
     * 购买个数
     */
    private Integer skuNum;
    /**
     * 工作单id
     */
    private Long taskId;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 1-已锁定  2-已解锁  3-扣减
     */
    private Integer lockStatus;

}

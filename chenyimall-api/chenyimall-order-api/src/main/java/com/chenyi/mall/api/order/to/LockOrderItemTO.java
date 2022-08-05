package com.chenyi.mall.api.order.to;

import lombok.Data;

/**
 * @author chenyi
 * @className LockOrderItemTO
 * @date 2022/7/31 15:03
 */
@Data
public class LockOrderItemTO {

    private Long skuId;

    private String skuName;

    private Integer skuQuantity;

    private Long orderId;

    private String orderSn;

}

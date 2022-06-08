package com.chenyi.gulimall.product.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenyi
 * @className SpuBoundTO
 * @date 2022/5/12 10:59
 */
@Data
public class SpuBoundTO {

    private String spuId;

    private BigDecimal buyBounds;

    private BigDecimal growBounds;

}

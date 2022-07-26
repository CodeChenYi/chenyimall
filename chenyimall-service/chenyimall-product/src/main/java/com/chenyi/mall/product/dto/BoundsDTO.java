package com.chenyi.mall.product.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenyi
 * @className Bounds
 * @date 2022/5/10 0:00
 */
@Data
public class BoundsDTO {

    private BigDecimal buyBounds;

    private BigDecimal growBounds;
}

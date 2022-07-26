package com.chenyi.mall.product.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenyi
 * @className MemberPriceDTO
 * @date 2022/5/10 0:18
 */
@Data
public class MemberPriceDTO {
    private String id;

    private String name;

    private BigDecimal price;
}

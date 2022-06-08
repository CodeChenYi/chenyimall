package com.chenyi.gulimall.product.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenyi
 * @className SkuReductionTO
 * @date 2022/5/12 11:31
 */
@Data
public class SkuReductionTO {

    private String skuId;
    /**
     * 全部数量
     */
    private int fullCount;
    /**
     * sku折扣
     */
    private BigDecimal discount;
    /**
     * 数量打折状态
     */
    private int countStatus;
    /**
     * sku总价
     */
    private BigDecimal fullPrice;
    /**
     * sku优惠价格
     */
    private BigDecimal reducePrice;
    /**
     * sku价格打折状态
     */
    private int priceStatus;
    /**
     * 会员价格
     */
    private List<MemberPriceDTO> memberPrice;
}

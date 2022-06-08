package com.chenyi.gulimall.product.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/** sku DTO
 * @author chenyi
 * @className SkuDTO
 * @date 2022/5/10 0:14
 */
@Data
public class SkuDTO {
    /**
     * sku销售属性DTO
     */
    private List<SkuSaleAttrDTO> attr;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * sku价格
     */
    private BigDecimal price;
    /**
     * sku标题
     */
    private String skuTitle;
    /**
     * sku副标题
     */
    private String skuSubtitle;
    /**
     * sku图片集
     */
    private List<SpuImagesDTO> images;
    /**
     * sku描述
     */
    private List<String> descar;
    /**
     * 全部数量
     */
    private int fullCount;
    /**
     * sku折扣价格
     */
    private BigDecimal discount;
    /**
     * 技术状态
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
     * sku价格状态
     */
    private int priceStatus;
    /**
     * 会员价格
     */
    private List<MemberPriceDTO> memberPrice;
}

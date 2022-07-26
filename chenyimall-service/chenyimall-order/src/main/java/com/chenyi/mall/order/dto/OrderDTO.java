package com.chenyi.mall.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenyi
 * @className OrderDTO
 * @date 2022/7/1 18:50
 */
@Data
public class OrderDTO {

    /**
     * 购物车选中商品skuId
     */
    private List<Long> cartItemSkuId;

    /**
     * member_id
     */
    private Long memberId;

    /**
     * 使用的优惠券
     */
    private Long couponId;

    /**
     * 用户名
     */
    private String memberUsername;
    /**
     * 订单总额
     */
    private BigDecimal totalAmount;
    /**
     * 应付总额
     */
    private BigDecimal payAmount;
    /**
     * 运费金额
     */
    private BigDecimal freightAmount;
    /**
     * 促销优化金额（促销价、满减、阶梯价）
     */
    private BigDecimal promotionAmount;
    /**
     * 积分抵扣金额
     */
    private BigDecimal integrationAmount;
    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponAmount;
    /**
     * 后台调整订单使用的折扣金额
     */
    private BigDecimal discountAmount;

    /**
     * 发票类型[0->不开发票；1->电子发票；2->纸质发票]
     */
    private Integer billType;
    /**
     * 发票抬头
     */
    private String billHeader;
    /**
     * 发票内容
     */
    private String billContent;
    /**
     * 收票人电话
     */
    private String billReceiverPhone;
    /**
     * 收票人邮箱
     */
    private String billReceiverEmail;

    /**
     * 用户收货地址id
     */
    private Long memberAddressId;

    /**
     * 省份/直辖市
     */
    private String receiverProvince;
    /**
     * 城市
     */
    private String receiverCity;
    /**
     * 区
     */
    private String receiverRegion;
    /**
     * 详细地址
     */
    private String receiverDetailAddress;
    /**
     * 订单备注
     */
    private String note;
    /**
     * 下单时使用的积分
     */
    private Integer useIntegration;

}

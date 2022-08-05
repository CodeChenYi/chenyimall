package com.chenyi.mall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenyi
 * @className OrderReturnInfoVO
 * @date 2022/7/30 16:46
 */
@Data
public class OrderBackInfoVO {

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 应付金额
     */
    private BigDecimal payAmount;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人手机号
     */
    private String receiverPhone;

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
     * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
     */
    private Integer status;

}

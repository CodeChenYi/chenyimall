package com.chenyi.mall.common.constant;

/**
 * @author chenyi
 * @className ChenYiMallContant
 * @date 2022/5/7 17:48
 */
public interface ChenYiMallConstant {

    /**
     * 空字符串
     */
    String NULL_STRING = "";

    /**
     * String = key
     */
    String STRING_KEY = "key";

    /**
     * 查询全部
     */
    String GET_ALL = "0";

    /**
     * 登录key loginUser
     */
    String LOGIN_USER = "loginUser::";

    /**
     * 购物车key
     */
    String CART_USER = "cartUser::";

    /**
     * request id字符串
     */
    String REQUEST_ID = "RequestId";

    /**
     * 准备提交订单
     */
    String READY_SUBMIT_ORDER = "ReadySubmitOrder::";

    /**
     * 7天毫秒值
      */
    Long SEVEN_DAY_MILLIS_VALUE = 1000L * 60 * 60 * 24 * 7;

    /**
     * 十五天毫秒值
     */
    Long FIFTEEN_DAY_MILLIS_VALUE = 1000L * 60 * 60 * 24 * 15;

    /**
     * 三十分钟毫秒值
     */
    Long THIRTY_MINUTE_MILLIS_VALUE = 1000L * 60 * 30;

    /**
     * 支付宝支付
     */
    Integer ALIPAY = 1;

    /**
     * 微信支付
     */
    Integer WECHAT_PAY = 2;

    /**
     * 银联支付
     */
    Integer UNION_PAY = 3;

    /**
     * 货到付款
     */
    Integer CASH_ON_DELIVERY = 4;

    /**
     * PC订单
     */
    Integer PC_ORDER = 0;

    /**
     * app订单
     */
    Integer APP_ORDER = 1;

    /**
     * 待付款
     */
    Integer PENDING_PAY = 0;

    /**
     * 待发货
     */
    Integer PENDING_SHIPMENT = 1;

    /**
     * 已发货
     */
    Integer SHIPPED = 2;

    /**
     * 已完成
     */
    Integer ORDER_COMPLETED = 3;

    /**
     * 已关闭
     */
    Integer ORDER_CLOSED = 4;

    /**
     * 无效订单
     */
    Integer INVALID_ORDER = 5;

    /**
     * 不开发票
     */
    Integer NO_BILL = 0;


    /**
     * 库存不足名称SKU ID
     */
    String PRODUCT_NO_STOCK_SKU = "库存不足的SKU：";

    /**
     * 库存已锁定
     */
    Integer WARE_ALREADY_LOCKED = 1;

    /**
     * 库存已解锁
     */
    Integer WARE_UNLOCKED = 2;

    /**
     * 库存已扣减
     */
    Integer WARE_DEDUCTION = 3;
}

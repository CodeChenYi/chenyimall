package com.chenyi.mall.common.enums;

/**
 * 错误码异常信息枚举类
 * 错误码定义规则为五位数字，前两位表示业务场景，后三位表示验证码
 * 错误码快速参考列表：
 * 10：通用，11：商品模块，12：订单模块，13：购物车模块，14：物流模块 15 用户模块，16 验证模块
 * 17 库存模块
 * 01为正常返回比如：11001，05为错误返回：比如11005
 * 正常返回：20000，数据校验错误：40000，服务器异常：50000，权限异常：41000
 * 路径不存在：40004，数据库已存在记录：55000
 *
 * @author chenyi
 * @className ResultEnum
 * @date 2022/5/7 20:25
 */
public enum ResultEnum {

    /**
     * 通用模块
     */
    JSON_HANDLER_ERROR(10005, "json处理异常"),
    SUCCESS(20000, "请求成功！"),
    PARAMETER_VERIFICATION_ERROR(40000, "参数校验错误！"),
    INTERNAL_SERVER_ERROR(50000, "服务器未知异常，请联系管理员！"),
    NO_PERMISSION(41000, "没有权限，请联系管理员授权"),
    PATH_NOT_EXIST(40004, "路径不存在！请检查请求路径"),
    SYSTEM_ALREADY_EXIST_RECORD(55000, "系统中已存在该记录"),

    /**
     * 验证模块
     */
    USER_AUTOGRAPH_VERIFY_FAIL(16005, "用户签名验证失败！"),

    /**
     * 用户模块
     */
    MEMBER_LOGIN_FAIL(15005, "用户名或密码错误！"),

    /**
     * 商品服务
     */
    REMOTE_CALL_FAIL(11005, "商品模块远程调用失败！"),

    /**
     * 订单服务
     */
    ILLEGAL_ORDER_REQUEST(12005, "非法的订单请求！"),
    DUPLICATE_CREATED_ORDER(12002, "订单已经创建请勿重复提交订单！"),
    ORDER_CREATED_FAIL(12005, "订单创建失败"),

    /**
     * 库存服务
     */
    LOCK_STOCK_FAIL(17005, "锁定库存失败"),
    STOCK_UN_LOCK_FAIL(17105, "库存解锁失败！"),
    PRODUCT_NO_STOCK(17005, "当前商品库存不足，Sku为：");


    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

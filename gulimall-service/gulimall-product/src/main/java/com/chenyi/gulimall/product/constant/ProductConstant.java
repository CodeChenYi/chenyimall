package com.chenyi.gulimall.product.constant;

/**
 * @author chenyi
 * @className ProductConstant
 * @date 2022/5/7 17:23
 */
public enum ProductConstant {

    ATTR_SALE_TYPE(0, "销售属性"),

    ATTR_BASE_TYPE(1, "基本属性");

    private Integer type;

    private String msg;

    ProductConstant(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

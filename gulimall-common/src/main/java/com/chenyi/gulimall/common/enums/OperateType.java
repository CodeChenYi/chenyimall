package com.chenyi.gulimall.common.enums;

/**
 * 操作类型枚举类
 * @author chenyi
 * @className OperateType
 * @date 2022/5/8 0:56
 */
public enum OperateType {

    INSERT("INSERT"),

    UPDATE("UPDATE"),

    SELECT("SELECT"),

    DELETE("DELETE"),

    OTHER("OTHER");

    private String msg;

    OperateType(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

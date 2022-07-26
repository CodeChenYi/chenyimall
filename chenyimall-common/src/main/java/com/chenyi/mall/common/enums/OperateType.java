package com.chenyi.mall.common.enums;

/**
 * 操作类型枚举类
 * @author chenyi
 * @className OperateType
 * @date 2022/5/8 0:56
 */
public enum OperateType {

    /**
     * 添加
     */
    INSERT("INSERT"),

    /**
     * 修改
     */
    UPDATE("UPDATE"),

    /**
     * 查询
     */
    SELECT("SELECT"),

    /**
     * 删除
     */
    DELETE("DELETE"),

    /**
     * 其他
     */
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

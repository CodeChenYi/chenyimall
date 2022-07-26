package com.chenyi.mall.common.exception;

/**
 * @author chenyi
 * @className ChenYiMallException
 * @date 2022/5/7 20:53
 */
public class ChenYiMallException extends RuntimeException{

    private Integer code;

    private String msg;

    public ChenYiMallException() {
        super();
    }

    public ChenYiMallException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ChenYiMallException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
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

package com.chenyi.gulimall.common.exception;

/**
 * @author chenyi
 * @className GuliMallException
 * @date 2022/5/7 20:53
 */
public class GuliMallException extends RuntimeException{

    private Integer code;

    private String msg;

    public GuliMallException() {
        super();
    }

    public GuliMallException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public GuliMallException(Integer code, String msg, Throwable cause) {
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

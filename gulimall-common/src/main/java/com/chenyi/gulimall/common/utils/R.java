package com.chenyi.gulimall.common.utils;

import com.chenyi.gulimall.common.enums.ResultEnum;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", ResultEnum.SUCCESS.getCode());
        put("msg", ResultEnum.SUCCESS.getMsg());
    }

    public static R error() {
        return error(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static R isOk(boolean b) {
        return b ? R.ok() : R.error();
    }

    public static R isOk(int i) {
        return i > 1 ? R.ok() : R.error();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Object get(String key) {
        return super.get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return JSONUtils.parseObject(JSONUtils.toJSONString(get(key)), clazz);
    }

    public <T> T get(String key, TypeReference<T> typeReference) {
        return JSONUtils.parseObject(JSONUtils.toJSONString(get(key)), typeReference);
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        return JSONUtils.parseList(JSONUtils.toJSONString(get(key)), clazz);
    }

    public Integer getCode() {
        return (Integer) this.get("code");
    }
}

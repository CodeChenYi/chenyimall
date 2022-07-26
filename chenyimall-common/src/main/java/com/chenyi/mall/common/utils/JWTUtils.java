package com.chenyi.mall.common.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.chenyi.mall.common.constant.ChenYiMallContant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author chenyi
 * @className JWTUtils
 * @date 2022/5/17 19:46
 */
public class JWTUtils {

    private static final Map<String, Object> PAYLOAD = new HashMap<>(2);

    private static volatile JWTUtils jwtUtils;

    private JWTUtils() {
    }

    static {
        PAYLOAD.put("time", System.currentTimeMillis() + ChenYiMallContant.SEVEN_DAY_MILLIS_VALUE);
    }

    public static JWTUtils getInstance() {
        if (jwtUtils == null) {
            synchronized (JWTUtils.class) {
                if (jwtUtils == null) {
                    jwtUtils = new JWTUtils();
                }
            }
        }
        return jwtUtils;
    }

    /**
     * 生成jwt
     * @param key 生成jwt的payload参数
     * @return jwtToken
     */
    public static String createToken(String key) {
        PAYLOAD.put("uid", key);
        return JWTUtil.createToken(PAYLOAD, key.getBytes());
    }

    /**
     * 生成jwt
     * @param key 生成jwt的payload参数
     * @param timeOut 过期时间
     * @param timeUnit 过期时间单位
     * @return jwtToken
     */
    public static String createToken(String key, Long timeOut, TimeUnit timeUnit) {
        PAYLOAD.put("uid", key);
        PAYLOAD.put("time", System.currentTimeMillis() + timeUnit.toMillis(timeOut));
        return JWTUtil.createToken(PAYLOAD, key.getBytes());
    }

    /**
     * 通过jwt获取信息
     * @param token jwt值
     * @return
     */
    public static Object getPayload(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return jwt.getPayload("uid");
    }

    /**
     * 通过jwt获取信息
     * @param token jwtToken
     * @param payloadKey payload key
     * @return
     */
    public static Object getPayload(String token, String payloadKey) {
        JWT jwt = JWTUtil.parseToken(token);
        return jwt.getPayload(payloadKey);
    }

    /**
     * 验证jwt
     * @param token jwtToken
     * @return
     */
    public static boolean verify(String token, String key) {
        return JWTUtil.verify(token, key.getBytes());
    }

    public JWTUtils addPayload(String key, Object value) {
        PAYLOAD.put(key, value);
        return this;
    }

    public JWTUtils setTokenOverdueTime(Long timeOut, TimeUnit timeUnit) {
        PAYLOAD.put("time", System.currentTimeMillis() + timeUnit.toMillis(timeOut));
        return this;
    }

    public String build(String key) {
        String token = JWTUtil.createToken(PAYLOAD, key.getBytes());
        PAYLOAD.keySet().stream().filter("time"::equals).forEach(PAYLOAD::remove);
        return token;
    }


}

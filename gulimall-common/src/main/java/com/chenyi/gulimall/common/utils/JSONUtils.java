package com.chenyi.gulimall.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * jackson 操作工具类
 *
 * @author chenyi
 * @className JSONUtils
 * @date 2022/6/8 16:13
 */
@Slf4j
public class JSONUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将json字符串转换为java对象
     *
     * @param json       json字符串
     * @param returnType 返回类型
     * @param <E>        具体返回类型
     * @return 返回java对象
     */
    @SneakyThrows
    public static <E> E parseObject(String json, Class<E> returnType) {
        return MAPPER.readValue(json, returnType);
    }

    /**
     * 将json字符串转换为java对象
     *
     * @param json       json字符串
     * @param returnType 返回类型
     * @param <E>        具体返回类型
     * @return 返回java对象
     */
    @SneakyThrows
    public static <E> E parseObject(String json, TypeReference<E> returnType) {
        return MAPPER.readValue(json, returnType);
    }

    /**
     * 将json字符串转换为List对象
     *
     * @param json     json字符串
     * @param listType list泛型类型
     * @param <E>      list泛型类型
     * @return 返回list
     */
    @SneakyThrows
    public static <E> List<E> parseList(String json, Class<E> listType) {
        return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, listType));
    }

//    @SneakyThrows
//    public static <K, V> List<Map<K, V>> parseList(String json, Class<K> key, Class<V> value) {
//        parseList(parseList(json, Map.class)
//    }

    /**
     * 将json转换为map
     *
     * @param json      json字符串
     * @param keyType   key类型
     * @param valueType value类型
     * @param <K>       key泛型类
     * @param <V>       value泛型类型
     * @return 返回map集合
     */
    @SneakyThrows
    public static <K, V> Map<K, V> parseMap(String json, Class<K> keyType, Class<V> valueType) {
        return MAPPER.readValue(json, MAPPER.getTypeFactory().constructMapType(Map.class, keyType, valueType));
    }

    /**
     * 将java对象转换为json字符串
     *
     * @param o java对象
     * @return json字符串
     */
    @SneakyThrows
    public static String toJSONString(Object o) {
        return MAPPER.writeValueAsString(o);
    }

}

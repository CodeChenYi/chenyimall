package com.chenyi.mall.common.utils;

import com.chenyi.mall.common.to.RedisData;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * redis工具类带有ByStrTemp的代表使用的是StringRedisTemplate
 * @author chenyi
 * @className RedisUtils
 * @date 2022/9/22 9:26
 */
@Component
public class RedisUtils<K, V> {

    @Resource
    RedisTemplate<K, V> redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 返回String数据
     * @param key
     * @return
     */
    public String getStringByStrTemp(String key) {
        return stringRedisTemplate.opsForValue()
                .get(key);
    }

    /**
     * 返回Map<String, List<T>>类型数据
     * @param key 获取的key
     * @param typeReference T类型数据
     * @return
     * @param <T> 类型值
     */
    public <T> Map<String, List<T>> getStringListMapByStrTemp(String key, TypeReference<Map<String, List<T>>> typeReference) {
        String categoryLevelJson = stringRedisTemplate
                .opsForValue().get(key);
        if (!StringUtils.isEmpty(categoryLevelJson)) {
            return JSONUtils.parseObject(categoryLevelJson, typeReference);
        }
        return null;
    }

    /**
     * 返回Map<String, List<T>>类型数据
     * @param key 获取的key
     * @return
     */
    public V getValue(K key) {
        V redisData = redisTemplate
                .opsForValue().get(key);
        if (redisData != null) {
            return redisData;
        }
        return null;
    }

    public Object getRedisData(K key) {
        RedisData redisData = (RedisData) redisTemplate.opsForValue().get(key);
        if (redisData != null) {
            return redisData.getData();
        }
        return null;
    }

    public boolean setString(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    /**
     * 使用stringRedisTemplate.opsForValue().set(key, value);
     * @param key
     * @param value
     * @return
     */
    public boolean setStringByStrTemp(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return true;
    }

    /**
     * 主要是用于判断已经封装的RedisData对象中key是否过期
     * @param key
     * @return
     */
    public boolean keyIsExpired(K key) {
        RedisData redisData = (RedisData) redisTemplate.opsForValue().get(key);
        if (redisData == null) {
            return true;
        }
        LocalDateTime expireTime = redisData.getExpireTime();
        return expireTime.isBefore(LocalDateTime.now());
    }

}

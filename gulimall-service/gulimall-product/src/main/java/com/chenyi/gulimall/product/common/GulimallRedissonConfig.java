package com.chenyi.gulimall.product.common;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 使用原生方式导入Redisson
 */
//@Configuration
public class GulimallRedissonConfig {

//    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://111.229.191.225:6379").setPassword("5201314xuan");
        return Redisson.create(config);
    }

}

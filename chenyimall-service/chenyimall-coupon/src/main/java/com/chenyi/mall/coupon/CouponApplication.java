package com.chenyi.mall.coupon;

import com.chenyi.mall.common.config.mq.RabbitConfig;
import com.chenyi.mall.common.config.redis.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author by chenyi
 * @className CouponApplication
 * @date 2021/9/25 15:46
 */
@EnableFeignClients("com.chenyi.mall")
@ComponentScan(value = "com.chenyi.mall", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {RabbitConfig.class, RedisConfig.class})
})
@EnableDiscoveryClient
@SpringBootApplication(exclude = { RabbitAutoConfiguration.class,
        RedisAutoConfiguration.class
})
public class CouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class, args);
    }
}

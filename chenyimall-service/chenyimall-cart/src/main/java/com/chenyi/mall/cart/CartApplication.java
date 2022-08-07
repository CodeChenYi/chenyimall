package com.chenyi.mall.cart;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.chenyi.mall.common.config.mq.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author chenyi
 * @className CartApplication
 * @date 2022/6/28 23:04
 */
@EnableFeignClients("com.chenyi.mall")
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        RabbitAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@ComponentScan(value = "com.chenyi.mall",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = RabbitConfig.class)})
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}

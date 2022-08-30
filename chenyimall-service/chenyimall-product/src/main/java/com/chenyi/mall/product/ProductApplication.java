package com.chenyi.mall.product;

import com.chenyi.mall.common.config.mq.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author by chenyi
 * @className ProductApplication
 * @date 2021/9/25 18:20
 */
@EnableFeignClients("com.chenyi.mall")
@EnableDiscoveryClient
@SpringBootApplication(exclude = {RabbitAutoConfiguration.class})
@ComponentScan(value = "com.chenyi.mall",  excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = RabbitConfig.class)
})
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}

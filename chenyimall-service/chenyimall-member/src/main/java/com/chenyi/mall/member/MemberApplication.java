package com.chenyi.mall.member;

import com.chenyi.mall.common.config.mq.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author ChenYi
 */
@EnableFeignClients("com.chenyi.mall")
@ComponentScan(value = "com.chenyi.mall",
        excludeFilters = {@ComponentScan.Filter(type =
        FilterType.ASSIGNABLE_TYPE, classes = RabbitConfig.class)})
@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
@EnableDiscoveryClient
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}

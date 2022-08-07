package com.chenyi.mall.third.party;

import com.chenyi.mall.common.config.mq.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author chenyi
 * @className ThirdPartyApplication
 * @date 2021/11/6 16:41
 */
@ComponentScan(value = "com.chenyi.mall", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = RabbitConfig.class)})
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        RabbitAutoConfiguration.class})
public class ThirdPartyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThirdPartyApplication.class, args);
    }
}

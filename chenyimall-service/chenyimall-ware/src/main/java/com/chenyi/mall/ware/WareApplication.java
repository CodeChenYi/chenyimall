package com.chenyi.mall.ware;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * @className WareApplication
 * @author ChenYi
 * @date 2021/11/30
 */
@EnableRabbit
@EnableTransactionManagement
@EnableFeignClients("com.chenyi.mall")
@ComponentScan(value = "com.chenyi.mall")
@SpringBootApplication
@EnableDiscoveryClient
public class WareApplication {
    public static void main(String[] args) {
        SpringApplication.run(WareApplication.class, args);
    }
}

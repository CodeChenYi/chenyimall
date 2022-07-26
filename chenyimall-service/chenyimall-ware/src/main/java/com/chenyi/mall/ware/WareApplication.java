package com.chenyi.mall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ChenYi
 */
@EnableFeignClients("com.chenyi.mall")
@ComponentScan("com.chenyi.mall")
@SpringBootApplication
@EnableDiscoveryClient
public class WareApplication {
    public static void main(String[] args) {
        SpringApplication.run(WareApplication.class, args);
    }
}

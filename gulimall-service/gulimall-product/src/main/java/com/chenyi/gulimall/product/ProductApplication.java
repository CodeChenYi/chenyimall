package com.chenyi.gulimall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author by zixuan
 * @className ProductApplication
 * @date 2021/9/25 18:20
 */
@EnableFeignClients("com.chenyi.gulimall")
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.chenyi.gulimall")
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}

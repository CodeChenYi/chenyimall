package com.chenyi.gulimall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author by zixuan
 * @className ProductApplication
 * @date 2021/9/25 18:20
 */

@EnableDiscoveryClient
@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args){
        SpringApplication.run(ProductApplication.class, args);
    }
}

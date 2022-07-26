package com.chenyi.mall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author by zixuan
 * @className CouponApplication
 * @date 2021/9/25 15:46
 */
@EnableFeignClients("com.chenyi.mall")
@ComponentScan("com.chenyi.mall")
@EnableDiscoveryClient
@SpringBootApplication
public class CouponApplication {
    public static void main(String[] args){
        SpringApplication.run(CouponApplication.class, args);
    }
}
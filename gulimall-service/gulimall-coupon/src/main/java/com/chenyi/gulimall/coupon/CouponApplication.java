package com.chenyi.gulimall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author by zixuan
 * @className CouponApplication
 * @date 2021/9/25 15:46
 */

@EnableDiscoveryClient
@SpringBootApplication
public class CouponApplication {
    public static void main(String[] args){
        SpringApplication.run(CouponApplication.class, args);
    }
}

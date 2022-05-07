package com.chenyi.gulimall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author by chenyi
 * @className GuliMallGateWayApplication
 * @date 2021/12/6 21:52
 */

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(GatewayApplication.class, args);
    }
}

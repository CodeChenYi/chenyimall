package com.chenyi.mall.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className SecKillApplication
 * @author chenyi
 * @date 2022/9/30 17:49
 */
@EnableFeignClients("com.chenyi.mall")
@ComponentScan("com.chenyi.mall")
@EnableDiscoveryClient
@SpringBootApplication
public class SecKillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecKillApplication.class, args);
    }
}

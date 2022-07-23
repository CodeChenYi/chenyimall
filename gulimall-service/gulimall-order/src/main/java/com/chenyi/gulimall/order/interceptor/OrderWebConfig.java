package com.chenyi.gulimall.order.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chenyi
 * @className GulimallWebConfig
 * @date 2022/6/29 22:10
 */
@Configuration
public class OrderWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cartInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public OrderInterceptor cartInterceptor() {
        return new OrderInterceptor();
    }
}

package com.chenyi.mall.cart.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chenyi
 * @className mallWebConfig
 * @date 2022/6/29 22:10
 */
@Configuration
public class CartWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cartInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public CartInterceptor cartInterceptor() {
        return new CartInterceptor();
    }
}

package com.chenyi.mall.order.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 解决Feign远程调用丢失请求头的问题
 * 由于在远程调用会使用代理对象，会使用RequestInterceptor，
 * 我们只需要实现这个对象返回到容器中即可
 * @author chenyi
 * @className FeignRequestConfig
 * @date 2022/7/1 14:10
 */
@Configuration
public class FeignRequestConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                String cookie = request.getHeader("Cookie");
                requestTemplate.header("Cookie", cookie);
                String token = request.getHeader("token");
                requestTemplate.header("token", token);
            }
        };
    }

}

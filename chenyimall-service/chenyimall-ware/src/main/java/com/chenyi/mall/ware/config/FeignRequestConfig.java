package com.chenyi.mall.ware.config;

import com.chenyi.mall.common.utils.ServletUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author chenyi
 * @className FeignRequestConfig
 * @date 2022/7/1 14:10
 */
@Configuration
public class FeignRequestConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes servletRequestAttributes = ServletUtils.getServletRequestAttributes();
                if (servletRequestAttributes != null) {
                    HttpServletRequest request = servletRequestAttributes.getRequest();
                    String cookie = request.getHeader("Cookie");
                    requestTemplate.header("Cookie", cookie);
                    String token = request.getHeader("token");
                    requestTemplate.header("token", token);
                } else {
                    requestTemplate.header("RequestToken", UUID.randomUUID().toString());
                }
            }
        };
    }

}

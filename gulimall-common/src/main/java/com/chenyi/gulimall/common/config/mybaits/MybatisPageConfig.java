package com.chenyi.gulimall.common.config.mybaits;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ConditionalOnBean({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@EnableTransactionManagement
@MapperScan("com.chenyi.gulimall.*.mapper")
public class MybatisPageConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求最大页数后的操作
        paginationInterceptor.setOverflow(true);
        paginationInterceptor.setLimit(500);
        return paginationInterceptor;
    }

}

package com.chenyi.mall.common.config.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 设置rabbit MQ的解析格式为json
 * @author chenyi
 * @className RabbitConfig
 * @date 2022/6/30 22:18
 */
@Slf4j
@Configuration
public class RabbitConfig {

    @Bean
    public MessageConverter messageConverter() {
        log.info("-------------messageConverter--------------");
        return new Jackson2JsonMessageConverter();
    }

}

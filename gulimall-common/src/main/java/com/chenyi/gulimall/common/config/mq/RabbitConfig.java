package com.chenyi.gulimall.common.config.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenyi
 * @className RabbitConfig
 * @date 2022/6/30 22:18
 */
@Slf4j
@ConditionalOnBean(RabbitAutoConfiguration.class)
@Configuration
public class RabbitConfig {

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        // 开启交换机确认
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.error("=======ConfirmCallback=========");
            log.error("correlationData = {}", correlationData);
            log.error("ack = {}", ack);
            log.error("cause = {}", cause);
            log.error("=======ConfirmCallback=========");
        });

        // 开启队列失败确认
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.error("--------------ReturnCallback----------------");
            log.error("message = {}",  message);
            log.error("replyCode = {}", replyCode);
            log.error("replyText = {}", replyText);
            log.error("exchange = {}", exchange);
            log.error("routingKey = {}", routingKey);
            log.error("--------------ReturnCallback----------------");
        });
        return rabbitTemplate;
    }

}

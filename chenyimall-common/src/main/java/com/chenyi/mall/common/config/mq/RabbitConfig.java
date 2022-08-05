package com.chenyi.mall.common.config.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenyi
 * @className RabbitConfig
 * @date 2022/6/30 22:18
 */
@Slf4j
@ConditionalOnMissingBean(RabbitAutoConfiguration.class)
@Configuration
public class RabbitConfig {

    @Bean
    public MessageConverter messageConverter() {
        log.info("-------------messageConverter--------------");
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name = "rabbitTemplate")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());

        // 开启交换机确认
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            log.info("=======ConfirmCallback=========");
//            log.info("correlationData = {}", correlationData);
//            log.info("ack = {}", ack);
//            log.info("cause = {}", cause);
//            log.info("=======ConfirmCallback=========");
//        });

        // 开启队列失败确认
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            log.info("--------------ReturnCallback----------------");
//            log.info("message = {}",  message);
//            log.info("replyCode = {}", replyCode);
//            log.info("replyText = {}", replyText);
//            log.info("exchange = {}", exchange);
//            log.info("routingKey = {}", routingKey);
//            log.info("--------------ReturnCallback----------------");
//        });
        return rabbitTemplate;
    }

}

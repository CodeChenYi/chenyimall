package com.chenyi.mall.order.config;

import com.chenyi.mall.common.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author chenyi
 * @className RabiitMQConfig
 * @date 2022/7/30 17:05
 */
@Configuration
public class OrderRabbitConfig {

    @Bean
    public Queue orderDelayQueue() {
        // 设置死信路由
        HashMap<String, Object> map = new HashMap<>(3);
        map.put(RabbitConstant.X_DEAD_LETTER_EXCHANGE, RabbitConstant.ORDER_EVENT_EXCHANGE);
        map.put(RabbitConstant.X_DEAD_LETTER_ROUTING_KEY, RabbitConstant.ORDER_RELEASE_ORDER_KEY);
        map.put(RabbitConstant.X_MESSAGE_TTL, 60000);
        // 持久化，排他，自动删除
        return new Queue(RabbitConstant.ORDER_DELAY_QUEUE, true, false, false, map);
    }

    @Bean
    public Queue orderReleaseOrderQueue() {
        return new Queue(RabbitConstant.ORDER_RELEASE_ORDER_QUEUE, true, false, false);
    }

    @Bean
    public Exchange orderEventExchange() {
        return new TopicExchange(RabbitConstant.ORDER_EVENT_EXCHANGE, true, false);
    }

    @Bean
    public Binding orderCreateOrderBinding() {
        return new Binding(RabbitConstant.ORDER_DELAY_QUEUE,
                Binding.DestinationType.QUEUE,
                RabbitConstant.ORDER_EVENT_EXCHANGE,
                RabbitConstant.ORDER_CREATE_ORDER_KEY,
                null);
    }

    @Bean
    public Binding orderReleaseOrderBinding() {
        return new Binding(RabbitConstant.ORDER_RELEASE_ORDER_QUEUE,
                Binding.DestinationType.QUEUE,
                RabbitConstant.ORDER_EVENT_EXCHANGE,
                RabbitConstant.ORDER_RELEASE_ORDER_KEY,
                null);
    }

    @Bean
    public Binding orderReleaseOtherBinding() {
        return new Binding(RabbitConstant.STOCK_RELEASE_STOCK_QUEUE,
                Binding.DestinationType.QUEUE,
                RabbitConstant.ORDER_EVENT_EXCHANGE,
                RabbitConstant.ORDER_RELEASE_OTHER_ALL_KEY,
                null);
    }
}

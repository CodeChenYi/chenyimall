package com.chenyi.mall.ware.config;

import com.chenyi.mall.common.constant.RabbitConstant;
import com.chenyi.mall.common.utils.R;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyi
 * @className WareRabiitConfig
 * @date 2022/7/31 19:11
 */
@Configuration
public class WareRabbitConfig {

    @Bean
    public Exchange stockEventExchange() {
        return new TopicExchange(
                RabbitConstant.STOCK_EVENT_EXCHANGE,
                true,
                false);
    }

    @Bean
    public Queue stockReleaseStockQueue() {
        return new Queue(RabbitConstant.STOCK_RELEASE_STOCK_QUEUE,
                true,
                false,
                false);
    }

    @Bean
    public Queue stockDelayQueue() {
        Map<String, Object> map = new HashMap<>(3);
        map.put(RabbitConstant.X_DEAD_LETTER_EXCHANGE, RabbitConstant.STOCK_EVENT_EXCHANGE);
        map.put(RabbitConstant.X_DEAD_LETTER_ROUTING_KEY, RabbitConstant.STOCK_RELEASE_KEY);
        map.put(RabbitConstant.X_MESSAGE_TTL, 120000);
        return new Queue(RabbitConstant.STOCK_DELAY_QUEUE,
                true,
                false,
                false,
                map);
    }

    @Bean
    public Binding stockReleaseBinding() {
        return new Binding(RabbitConstant.STOCK_RELEASE_STOCK_QUEUE,
                Binding.DestinationType.QUEUE,
                RabbitConstant.STOCK_EVENT_EXCHANGE,
                RabbitConstant.STOCK_RELEASE_ALL_KEY,
                null);
    }

    @Bean
    public Binding stockLockedBinding() {
        return new Binding(RabbitConstant.STOCK_DELAY_QUEUE,
                Binding.DestinationType.QUEUE,
                RabbitConstant.STOCK_EVENT_EXCHANGE,
                RabbitConstant.STOCK_LOCK_KEY,
                null);
    }


}

package com.chenyi.mall.order.listener;

import com.chenyi.mall.common.constant.RabbitConstant;
import com.chenyi.mall.order.entity.OrderEntity;
import com.chenyi.mall.order.service.OrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author chenyi
 * @className OrderListener
 * @date 2022/7/30 17:53
 */
@Slf4j
@Service
@RabbitListener(queues = RabbitConstant.ORDER_RELEASE_ORDER_QUEUE)
public class OrderListener {

    @Resource
    private OrderService orderService;

    @RabbitHandler
    public void orderReleaseOrderListener(OrderEntity orderEntity,
                                          Message message,
                                          Channel channel) throws IOException {
        // 关闭订单
        log.info("------------订单超时准备关闭--------------");
        try {
            orderService.closeOrder(orderEntity);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}

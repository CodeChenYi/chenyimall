package com.chenyi.mall.ware.listener;

import com.chenyi.mall.api.order.to.OrderTO;
import com.chenyi.mall.api.ware.to.StockLockTO;
import com.chenyi.mall.common.constant.RabbitConstant;
import com.chenyi.mall.ware.service.WareSkuService;
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
 * @className WareLockListener
 * @date 2022/7/31 17:40
 */
@Slf4j
@Service
@RabbitListener(queues = RabbitConstant.STOCK_RELEASE_STOCK_QUEUE)
public class WareLockListener {

    @Resource
    private WareSkuService wareSkuService;

    /**
     * 库存解锁相关逻辑
     *
     * @param stockLockTO
     * @param message
     */
    @RabbitHandler
    public void handlerOrderLockRelease(StockLockTO stockLockTO, Message message, Channel channel) throws IOException {
        // 库存补偿解锁逻辑
        log.info("---------------库存补偿解锁------------------");
        try {
            wareSkuService.unLockStock(stockLockTO);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

    @RabbitHandler
    public void handlerOrderCloseRelease(OrderTO order, Message message, Channel channel) throws IOException {
        // 订单关闭主动解锁库存
        log.info("-----------订单关闭主动解锁库存----------------");
        try {
            wareSkuService.unLockStock(order);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

}

package com.chenyi.mall.common.constant;

/**
 * @author chenyi
 * @className RabbitContant
 * @date 2022/7/30 17:29
 */
public interface RabbitConstant {

    String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

    String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    String X_MESSAGE_TTL = "x-message-ttl";

    String ORDER_EVENT_EXCHANGE = "order-event-exchange";

    String ORDER_DELAY_QUEUE = "order.delay.queue";

    String ORDER_RELEASE_ORDER_QUEUE = "order.release.order.queue";

    String STOCK_RELEASE_STOCK_QUEUE = "stock.release.stock.queue";

    String ORDER_RELEASE_ORDER_KEY = "order.release.order";


    String ORDER_RELEASE_OTHER_KEY = "order.release.other";

    String ORDER_RELEASE_OTHER_ALL_KEY = "order.release.other.#";

    String ORDER_CREATE_ORDER_KEY = "order.create.order";

    String STOCK_LOCK_KEY = "stock.lock";

    String STOCK_EVENT_EXCHANGE = "stock.event.exchange";
    String STOCK_DELAY_QUEUE = "stock.delay.queue";
    String STOCK_RELEASE_KEY = "stock.release";

    String STOCK_RELEASE_ALL_KEY = "stock.release.#";
}

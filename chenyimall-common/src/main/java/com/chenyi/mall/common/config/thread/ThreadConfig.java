package com.chenyi.mall.common.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chenyi
 * @className ThreadConfig
 * @date 2022/5/14 21:44
 */
@Data
@Component
@ConfigurationProperties("chenyimall.thread")
public class ThreadConfig {

    /**
     * 是否启用线程池
     */
    private boolean enableThread = true;

    /**
     * 核心线程数
     */
    private int core = 20;

    /**
     * 最大线程数
     */
    private int maxPoolSize = 200;

    /**
     * 空闲时间
     */
    private int time = 10;

    /**
     * 队列大小
     */
    private int queueSize = 10000;

//    private TimeUnit unit = TimeUnit.SECONDS;
//
//    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(100000);
//
//    private ThreadFactory factory = Executors.defaultThreadFactory();
//
//    private RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
}

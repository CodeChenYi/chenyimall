package com.chenyi.mall.common.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 自定义线程池配置类
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
    private int queueSize = 100;

    /**
     * 超时时间
     */
    private TimeUnit unit = TimeUnit.SECONDS;

//    /**
//     * 阻塞队列
//     */
//    private Class<? extends BlockingQueue> queue = ArrayBlockingQueue.class;
//
//    /**
//     * 线程工厂
//     */
//    private Class<? extends ThreadFactory> factory = Executors.defaultThreadFactory().getClass();
//
//    /**
//     * 拒绝策略
//     */
//    private Class<? extends RejectedExecutionHandler> handler = ThreadPoolExecutor.DiscardPolicy.class;
}

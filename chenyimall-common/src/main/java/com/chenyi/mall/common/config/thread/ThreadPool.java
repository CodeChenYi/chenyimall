package com.chenyi.mall.common.config.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenyi
 * @className ThreadPool
 * @date 2022/5/14 21:39
 */
//@EnableConfigurationProperties(ThreadPool.class)
@Configuration
public class ThreadPool {

    @Resource
    ThreadConfig threadConfig;

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        if (threadConfig.isEnableThread()) {
            return new ThreadPoolExecutor(
                    threadConfig.getCore(),
                    threadConfig.getMaxPoolSize(),
                    threadConfig.getTime(),
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(threadConfig.getQueueSize()),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.DiscardPolicy());
        }
        return null;
    }

}

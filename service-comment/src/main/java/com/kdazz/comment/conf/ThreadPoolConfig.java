package com.kdazz.comment.conf;

import com.kdazz.comment.utils.CustomBlockingQueue;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Configuration
@Slf4j
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor handlerOrderExecutor(@NotNull ServerConfigurer serverConfig) {
        return new ThreadPoolExecutor(serverConfig.getCorePoolSize(), serverConfig.getMaxPoolSize(),
                60, TimeUnit.SECONDS, new CustomBlockingQueue<>(serverConfig.getQueueCapacity()));
    }
}

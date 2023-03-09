package com.kdazz.comment.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ServerConfigurer {

    @Value("${callback.corePoolSize}")
    private int corePoolSize;

    @Value("${callback.maxPoolSize}")
    private int maxPoolSize;

    @Value("${callback.queueCapacity}")
    private int queueCapacity;

    @Value("${callback.handlerMaxSize}")
    private int handlerMaxSize;

}
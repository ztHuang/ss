package com.huang.web.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置
 * @Author huangzt
 * @Date 2019.04.13
 * @Version 1.0
 */

@Configuration
public class MQConfig {

    public static final String QUEUE = "queue";

    /**
     * 消息队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(QUEUE, true);
    }
}

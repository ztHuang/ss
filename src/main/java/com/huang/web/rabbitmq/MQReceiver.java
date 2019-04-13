package com.huang.web.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 接收者
 * @Author huangzt
 * @Date 2019.04.13
 * @Version 1.0
 */

@Service
public class MQReceiver {

    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    //监听对象
    @RabbitListener(queues = MQConfig.QUEUE)
    public void receiver(String message){
        logger.info("receiver ====== >>>>>" + message);

    }
}

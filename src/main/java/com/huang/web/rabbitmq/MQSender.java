package com.huang.web.rabbitmq;

import com.huang.web.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发送者
 * @Author huangzt
 * @Date 2019.04.13
 * @Version 1.0
 */

@Service
public class MQSender {

    private static Logger logger = LoggerFactory.getLogger(MQSender.class);


    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(Object message) {
        String msg = ToolUtil.bean2String(message);
        logger.info("send    ====== >>>>" + msg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
    }
}

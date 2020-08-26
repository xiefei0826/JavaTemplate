package com.example.demo.service.impl;

import com.example.demo.model.activemq.User;
import com.example.demo.service.RabbitMqService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitMqServiceImpl implements RabbitTemplate.ConfirmCallback, RabbitMqService {
    private static final Logger logger = Logger.getLogger(RabbitMqServiceImpl.class.getName());
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;
    @Value("${rabbitmq.queue.user}")
    private String userQueueName;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg(String msg) {

        logger.info("send message:" + msg);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(msgQueueName,msg);
    }

    @Override
    public void sendUser(User user) {

        logger.info("send message:" + user);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(userQueueName,user);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

        if (b)
            logger.info("consume ok");
        else
            logger.info("consume fail");
    }
}

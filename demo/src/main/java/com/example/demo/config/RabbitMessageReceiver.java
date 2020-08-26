package com.example.demo.config;

import com.example.demo.model.activemq.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitMessageReceiver {
    private static final Logger logger = Logger.getLogger(RabbitMessageReceiver.class.getName());


    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiveMsg(String msg) {
        logger.info("receive message:" + msg);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.user}"})
    public void receiveUserMsg(User user) {
        logger.info("receive message:" + user);
    }
}

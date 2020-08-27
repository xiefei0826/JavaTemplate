package com.example.demo.service.impl;

import com.example.demo.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ActiveMqServiceImpl implements ActiveMqService {
    private static Logger logger = Logger.getLogger(ActiveMqServiceImpl.class.getName());
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMsg(String message) {
        logger.info("send message:" + message);
        logger.info("test");
        jmsTemplate.convertAndSend(message);

    }

    @Override
    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void ReceiveMsg(String message) {
        logger.info("receive message:" + message);
    }
}

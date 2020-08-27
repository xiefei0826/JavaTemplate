package com.example.demo.service.impl;

import com.example.demo.model.mq.User;
import com.example.demo.service.ActiveMqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ActiveMqUserServiceImpl implements ActiveMqUserService {
    private static Logger logger = Logger.getLogger(ActiveMqUserServiceImpl.class.getName());
    private static final  String testDestination="testDestination";
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMsg(User user) {

        logger.info("send message :" + user);
        jmsTemplate.convertAndSend(testDestination,user);
    }

    @Override
    @JmsListener(destination =testDestination)
    public void ReceiveMsg(User user) {

        logger.info("receice message:" + user);
    }
}

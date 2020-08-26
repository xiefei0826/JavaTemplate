package com.example.demo.service;

import com.example.demo.model.activemq.User;

public interface RabbitMqService {
    void sendMsg(String msg);
    void  sendUser(User user);
}

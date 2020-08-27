package com.example.demo.service;

import com.example.demo.model.mq.User;

public interface RabbitMqService {
    void sendMsg(String msg);
    void  sendUser(User user);
}

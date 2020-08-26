package com.example.demo.service;


import com.example.demo.model.activemq.User;

public interface ActiveMqUserService {
    void sendMsg(User user);

    void ReceiveMsg(User user);
}

package com.example.demo.service;


import com.example.demo.model.mq.User;

public interface ActiveMqUserService {
    void sendMsg(User user);

    void ReceiveMsg(User user);
}

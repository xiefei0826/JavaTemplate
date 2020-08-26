package com.example.demo.service;

public interface ActiveMqService {
    void sendMsg(String message);

    void ReceiveMsg(String message);
}

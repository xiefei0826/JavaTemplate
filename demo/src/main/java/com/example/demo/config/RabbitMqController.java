package com.example.demo.config;

import com.example.demo.model.activemq.User;
import com.example.demo.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    @Autowired
    private RabbitMqService rabbitMqService;

    @GetMapping("/msg")
    public String msg(String message) {
        rabbitMqService.sendMsg(message);
        return "ok";
    }

    @PostMapping("/user")
    public String user(@RequestBody User user){
        rabbitMqService.sendUser(user);
        return "ok";
    }
}

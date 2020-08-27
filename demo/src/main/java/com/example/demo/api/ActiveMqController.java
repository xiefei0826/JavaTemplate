package com.example.demo.api;

import com.example.demo.model.mq.User;
import com.example.demo.service.ActiveMqService;
import com.example.demo.service.ActiveMqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activemq")
public class ActiveMqController {
    @Autowired
    private ActiveMqService activeMqService;
    @Autowired
    private ActiveMqUserService activeMqUserService;

    @GetMapping("msg")
    public String strMsg(String message) {
        activeMqService.sendMsg(message);
        return "ok";
    }

    @PostMapping("/user")
    public String msg(@RequestBody User user) {
        activeMqUserService.sendMsg(user);
        return "ok";
    }

}

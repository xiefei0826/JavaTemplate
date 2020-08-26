package com.example.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication

@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;
    @Value("${rabbitmq.queue.user}")
    private String userQueueName;
    @Bean
    public Queue createQueueMsg(){
        return new Queue(msgQueueName,true);
    }
    @Bean
    public Queue createQueueUser(){
        return new Queue(userQueueName,true);
    }
}

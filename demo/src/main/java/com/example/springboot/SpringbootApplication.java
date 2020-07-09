package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
public class SpringbootApplication {

    public static void main(String[] args) {


        SpringApplication.run(SpringbootApplication.class, args);
    }

}

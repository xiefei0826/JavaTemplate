package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Test {
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Value("${test.a}")
    private String a;
}

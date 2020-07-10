package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataCentreConfig {
    @Value("${database.driverName}")
    private String driverName;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;
    @Value("${database.url}")
    private String url;
    public String getDriverName() {
        System.out.println("getDriverName");
        System.out.println(driverName);
        return driverName;
    }
    public void setDriverName(String driverName) {
        System.out.println("----------");
        System.out.println(driverName);
        this.driverName = driverName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println(username);

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

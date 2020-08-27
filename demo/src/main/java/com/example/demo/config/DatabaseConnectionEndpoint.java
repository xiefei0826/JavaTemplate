package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Endpoint(id = "dbcheck")
public class DatabaseConnectionEndpoint {
    private static final String Driver="com.mysql.cj.jdbc.Driver";
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @ReadOperation
    public String DbConnect(){
        try {
            Class.forName(Driver);
            var conn= DriverManager.getConnection(url,username,password);
            return "Ok";
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
    }
}

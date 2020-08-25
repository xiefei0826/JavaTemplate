package com.data.binlog.mysql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BinlogConfig {

    @Value("${binlog.user}")
    private String User;
    @Value("${binlog.password}")
    private String Password;
    @Value("${binlog.port}")
    private int Port;
    @Value("${binlog.db}")
    private String Database;
    @Value("${binlog.host}")
    private String Host;
    @Value("${binlog.logname}")
    private String LogName;
    @Value("${binlog.post}")
    private int post;
    @Value("${binlog.eshost}")
    private String ElasticSearchHost;
    @Value("${binlog.esport}")
    private int ElasticSearchPort;
    public String getElasticSearchHost() {
        return ElasticSearchHost;
    }

    public void setElasticSearchHost(String elasticSearchHost) {
        ElasticSearchHost = elasticSearchHost;
    }

    public int getElasticSearchPort() {
        return ElasticSearchPort;
    }

    public void setElasticSearchPort(int elasticSearchPort) {
        ElasticSearchPort = elasticSearchPort;
    }
    public String getLogName() {
        return LogName;
    }

    public int getPost() {
        return post;
    }

    public String getUser() {
        return User;
    }

    public String getPassword() {
        return Password;
    }

    public int getPort() {
        return Port;
    }

    public String getDatabase() {
        return Database;
    }

    public String getHost() {
        return Host;
    }
}

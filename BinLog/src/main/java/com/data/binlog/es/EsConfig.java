package com.data.binlog.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

public class EsConfig {

    public static RestHighLevelClient restHighLevelClient(String Host,int Port) {
        HttpHost node1 = new HttpHost(Host, Port, "http");
        RestClientBuilder builder = RestClient.builder(node1);
        return new RestHighLevelClient(builder);
    }

}
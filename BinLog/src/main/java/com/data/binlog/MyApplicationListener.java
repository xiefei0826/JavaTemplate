package com.data.binlog;

import com.alibaba.fastjson.JSON;
import com.data.binlog.es.EsConfig;
import com.data.binlog.model.mysql.BinLogInfo;
import com.data.binlog.mysql.BinlogConfig;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class MyApplicationListener implements ApplicationListener<ContextClosedEvent> {


    //    @Qualifier("selfBinaryLogClient")
    private final BinaryLogClient binaryLogClient;

    private final BinlogConfig binlogConfig;
    public MyApplicationListener(BinaryLogClient binaryLogClient, BinlogConfig binlogConfig) {
        this.binaryLogClient = binaryLogClient;
        this.binlogConfig = binlogConfig;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

        RestHighLevelClient restHighLevelClient = EsConfig.restHighLevelClient(binlogConfig.getElasticSearchHost(),binlogConfig.getElasticSearchPort());
        IndexRequest indexRequest = new IndexRequest("binloginfo");
        indexRequest.id("1");
        BinLogInfo binLogInfo = new BinLogInfo();
        binLogInfo.setId(1);
        binLogInfo.setPost(binaryLogClient.getBinlogPosition());
        binLogInfo.setFileName(binaryLogClient.getBinlogFilename());
        indexRequest.source(JSON.toJSONString(binLogInfo), XContentType.JSON);
        try {
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            restHighLevelClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

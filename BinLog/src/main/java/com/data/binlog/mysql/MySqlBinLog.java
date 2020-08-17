package com.data.binlog.mysql;

import com.data.binlog.es.EsConfig;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class MySqlBinLog {
    private static BinaryLogClient BinaryLogClient;

    private final BinlogConfig mySqlConfig;

    public MySqlBinLog(BinlogConfig mySqlConfig) {
        this.mySqlConfig = mySqlConfig;
    }

    @Bean
    @Order(1)
    public BinaryLogClient BinaryLogClient() throws IOException {
        System.out.println(mySqlConfig.getHost());
        BinaryLogClient client = new BinaryLogClient(mySqlConfig.getHost(), mySqlConfig.getPort(), mySqlConfig.getDatabase(), mySqlConfig.getUser(), mySqlConfig.getPassword());
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        client.setEventDeserializer(eventDeserializer);
        RestHighLevelClient restHighLevelClient = EsConfig.restHighLevelClient(mySqlConfig.getElasticSearchHost(),mySqlConfig.getElasticSearchPort());
        GetRequest request = new GetRequest("binloginfo", "1");
        try {
            if(restHighLevelClient.exists(request, RequestOptions.DEFAULT)){
                Map<String, Object> esConf = restHighLevelClient.get(request,RequestOptions.DEFAULT).getSource();

                restHighLevelClient.close();
                client.setBinlogPosition((int)esConf.get("post"));
                client.setBinlogFilename((String)esConf.get("fileName"));
            }
            else {
                client.setBinlogPosition(mySqlConfig.getPost());
                client.setBinlogFilename(mySqlConfig.getLogName());
            }
        }
        catch (Exception e){
            client.setBinlogPosition(mySqlConfig.getPost());
            client.setBinlogFilename(mySqlConfig.getLogName());
        }




        client.registerEventListener(new MemoryItemEventListener(mySqlConfig.getElasticSearchHost(),mySqlConfig.getElasticSearchPort()));
        BinaryLogClient = client;
        return client;
    }

    @Bean
    @Order(2)
    public void SelfMySqlBinLog() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> {
            try {
                BinaryLogClient.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}

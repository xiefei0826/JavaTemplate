package com.data.binlog.mysql;

import com.alibaba.fastjson.JSON;
import com.data.binlog.es.EsConfig;
import com.data.binlog.model.es.EsMemoryItemEntity;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemoryItemEventListener implements BinaryLogClient.EventListener {

    private MemoryItemEventListener(){

    }
    public MemoryItemEventListener(String esHost, int esPort) {
        EsHost = esHost;
        EsPort = esPort;
    }

    private String EsHost;
    private int EsPort;
    private long TableId;
    private String TableName;
    private String NeedTableName = "MemoryItem";

    @Override
    public void onEvent(Event event) {

        switch (event.getHeader().getEventType()) {
            // 更新行,可以用其他队列拓展。或用定时任务。
            case EXT_UPDATE_ROWS: {
                if (TableName.equals(NeedTableName)) {
                    UpdateRowsEventData data = event.getData();
                    List<Map.Entry<Serializable[], Serializable[]>> rows = data.getRows();

                    List<Serializable[]> list = new ArrayList<>(rows.size());
                    for (Map.Entry<Serializable[], Serializable[]> row : rows) {
                        list.add(row.getValue());
                    }
                    InsertEs(list);
                }
                break;
            }
            // 删除行,此处用redis订阅发布做功能
            case EXT_DELETE_ROWS: {
                if (TableName.equals(NeedTableName)) {
                    DeleteRowsEventData data = event.getData();
                    DeleteEs(data.getRows());
                }
                break;
            }
            // 插入行
            case EXT_WRITE_ROWS: {
                if (TableName.equals(NeedTableName)) {
                    WriteRowsEventData data = event.getData();
                    List<Serializable[]> rows = data.getRows();
                    InsertEs(rows);
                }
                break;
            }
            case TABLE_MAP: {
                TableMapEventData data = event.getData();
                TableId = data.getTableId();
                TableName = data.getTable();
                break;
            }
        }

    }

    private void InsertEs(List<Serializable[]> rows) {
        BulkRequest bulkRequest = new BulkRequest();
        for (Serializable[] row : rows) {
            IndexRequest index = new IndexRequest("memoryitem");
            index.id(row[0].toString());
            index.source(JSON.toJSONString(new EsMemoryItemEntity(row)), XContentType.JSON);
            bulkRequest.add(index);
        }
        RestHighLevelClient client = EsConfig.restHighLevelClient(EsHost,EsPort);
        try {
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void DeleteEs(List<Serializable[]> rows) {
        BulkRequest bulkRequest = new BulkRequest();
        for (Serializable[] row : rows) {
            DeleteRequest index = new DeleteRequest("memoryitem" );
            index.id(row[0].toString());
            bulkRequest.add(index);
        }
        RestHighLevelClient client = EsConfig.restHighLevelClient(EsHost,EsPort);
        try {
            client.bulk(bulkRequest,RequestOptions.DEFAULT);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

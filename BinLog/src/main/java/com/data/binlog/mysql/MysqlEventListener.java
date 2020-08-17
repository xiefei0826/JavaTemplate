package com.data.binlog.mysql;

import com.alibaba.fastjson.JSON;
import com.github.shyiko.mysql.binlog.BinaryLogClient.EventListener;
import com.github.shyiko.mysql.binlog.event.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * mysql只监听配置
 */
@Service
public class MysqlEventListener implements EventListener {

    @Override
    public void onEvent(Event event) {
        EventType mysqlEventType = event.getHeader().getEventType();
        switch (mysqlEventType) {
            // 更新行,可以用其他队列拓展。或用定时任务。
            case EXT_UPDATE_ROWS: {
                UpdateRowsEventData data = event.getData();
                System.out.println("Update-----"+JSON.toJSONString(data));
                break;
            }
            // 删除行,此处用redis订阅发布做功能
            case EXT_DELETE_ROWS: {
                DeleteRowsEventData data = event.getData();
                String temp = JSON.toJSONString(data);
                System.out.println("Delete-----"+temp);
                break;
            }
            // 插入行
            case EXT_WRITE_ROWS: {
                WriteRowsEventData data = event.getData();
                System.out.println( "Insert-----"+JSON.toJSONString(data));
                break;
            }
            // 查询行
            case UNKNOWN:
                break;
            case START_V3:
                break;
            case QUERY: {
//                QueryEventData data = event.getData();
//                if (!GetExcludeSql().contains(data.getSql())) {
//                    System.out.println(JSON.toJSONString(data));
//                }
                break;
            }
            case STOP:
                break;
            case ROTATE:
                break;
            case INTVAR:
                break;
            case LOAD:
                break;
            case SLAVE:
                break;
            case CREATE_FILE:
                break;
            case APPEND_BLOCK:
                break;
            case EXEC_LOAD:
                break;
            case DELETE_FILE:
                break;
            case NEW_LOAD:
                break;
            case RAND:
                break;
            case USER_VAR:
                break;
            case FORMAT_DESCRIPTION:
                break;
            case XID:
                break;
            case BEGIN_LOAD_QUERY:
                break;
            case EXECUTE_LOAD_QUERY:
                break;
            case TABLE_MAP:
            {
                TableMapEventData data = event.getData();
                System.out.println( "TABLE_MAP-----"+data.getTable()+"----"+data.getTableId());
            }
                break;
            case PRE_GA_WRITE_ROWS:
                break;
            case PRE_GA_UPDATE_ROWS:
                break;
            case PRE_GA_DELETE_ROWS:
                break;
            case WRITE_ROWS:
                break;
            case UPDATE_ROWS:
                break;
            case DELETE_ROWS:
                break;
            case INCIDENT:
                break;
            case HEARTBEAT:
                break;
            case IGNORABLE:
                break;
            case ROWS_QUERY:
                break;
            case GTID:
                break;
            case ANONYMOUS_GTID:
                break;
            case PREVIOUS_GTIDS:
                break;
            case TRANSACTION_CONTEXT:
                break;
            case VIEW_CHANGE:
                break;
            case XA_PREPARE:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + mysqlEventType);
        }
    }

    private static List<String> GetExcludeSql() {
        return Arrays.asList("BEGIN", "COMMIT");
    }

}

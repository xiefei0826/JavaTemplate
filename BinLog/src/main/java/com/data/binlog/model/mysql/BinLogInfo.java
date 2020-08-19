package com.data.binlog.model.mysql;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "binloginfo")
public class BinLogInfo {
    @Id
    private int Id;
    @Field(type = FieldType.Keyword)
    private String FileName;
    @Field(type = FieldType.Integer)
    private long Post;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public long getPost() {
        return Post;
    }

    public void setPost(long post) {
        Post = post;
    }
}

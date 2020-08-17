package com.data.binlog.model.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


@Document(indexName = "memoryitem")
public class EsMemoryItemEntity {

    public EsMemoryItemEntity(Serializable[] row) {
        id = (long) row[0];
        memoryLibId = (long) row[1];
        srcContent = new String((byte[]) row[2]);
        srcContentMd5 = new String((byte[]) row[3]);
        tgtContent = new String((byte[]) row[4]);
        tgtContentMd5 = new String((byte[]) row[5]);
        releaseNumber = (long) row[6];
        matching = (int) row[7];
        srcLanguage = (int) row[8];
        tgtLanguage = (int) row[9];
        seq = (int) row[10];
        updateTime = (long) row[11];
        createTime = (long) row[12];
        Status= (int) row[13];
    }

    @Id
    private Long id;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Long)
    private long memoryLibId;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String srcContent;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String srcContentMd5;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String tgtContent;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private String tgtContentMd5;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Long)
    private long releaseNumber;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Integer)
    private int matching;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Integer)
    private int srcLanguage;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Integer)
    private int tgtLanguage;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Integer)
    private int seq;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Long)
    private Long createTime;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Long)
    private Long updateTime;
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Integer)
    private int Status;
    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public long getMemoryLibId() {
        return memoryLibId;
    }

    public void setMemoryLibId(long memoryLibId) {
        this.memoryLibId = memoryLibId;
    }

    public String getSrcContent() {
        return srcContent;
    }

    public void setSrcContent(String srcContent) {
        this.srcContent = srcContent;
    }

    public String getSrcContentMd5() {
        return srcContentMd5;
    }

    public void setSrcContentMd5(String srcContentMd5) {
        this.srcContentMd5 = srcContentMd5;
    }

    public String getTgtContent() {
        return tgtContent;
    }

    public void setTgtContent(String tgtContent) {
        this.tgtContent = tgtContent;
    }

    public String getTgtContentMd5() {
        return tgtContentMd5;
    }

    public void setTgtContentMd5(String tgtContentMd5) {
        this.tgtContentMd5 = tgtContentMd5;
    }

    public long getReleaseNumber() {
        return releaseNumber;
    }

    public void setReleaseNumber(long releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public int getMatching() {
        return matching;
    }

    public void setMatching(int matching) {
        this.matching = matching;
    }

    public int getSrcLanguage() {
        return srcLanguage;
    }

    public void setSrcLanguage(int srcLanguage) {
        this.srcLanguage = srcLanguage;
    }

    public int getTgtLanguage() {
        return tgtLanguage;
    }

    public void setTgtLanguage(int tgtLanguage) {
        this.tgtLanguage = tgtLanguage;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EsMemoryItemEntity that = (EsMemoryItemEntity) o;
        return matching == that.matching &&
                srcLanguage == that.srcLanguage &&
                tgtLanguage == that.tgtLanguage &&
                seq == that.seq &&
                Objects.equals(id, that.id) &&
                Objects.equals(memoryLibId, that.memoryLibId) &&
                Objects.equals(srcContent, that.srcContent) &&
                Objects.equals(srcContentMd5, that.srcContentMd5) &&
                Objects.equals(tgtContent, that.tgtContent) &&
                Objects.equals(tgtContentMd5, that.tgtContentMd5) &&
                Objects.equals(releaseNumber, that.releaseNumber) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memoryLibId, srcContent, srcContentMd5, tgtContent, tgtContentMd5, releaseNumber, matching, srcLanguage, tgtLanguage, seq, createTime, updateTime);
    }


}

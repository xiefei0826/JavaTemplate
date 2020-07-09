package com.example.springboot.model;


import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "MemoryLib")
public class MemoryLib {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String Name;
    private long UserId;
    private long OrganizationId;
    private String Introduce;
    private byte IsPublic;
    private long NewReleaseNumber;
    @Generated(GenerationTime.INSERT)
    private Timestamp CreateTime;
    @Generated(GenerationTime.ALWAYS)
    private Timestamp UpdateTime;
    @Convert(converter = StatusConverter.class)
    private StatusType Status;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public long getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(long organizationId) {
        OrganizationId = organizationId;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String introduce) {
        Introduce = introduce;
    }

    public byte getIsPublic() {
        return IsPublic;
    }

    public void setIsPublic(byte isPublic) {
        IsPublic = isPublic;
    }

    public long getNewReleaseNumber() {
        return NewReleaseNumber;
    }

    public void setNewReleaseNumber(long newReleaseNumber) {
        NewReleaseNumber = newReleaseNumber;
    }

    public Timestamp getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Timestamp createTime) {
        CreateTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        UpdateTime = updateTime;
    }
    @Convert(converter = StatusConverter.class)
    public StatusType getStatus() {
        return Status;
    }

    public void setStatus(StatusType status) {
        Status = status;
    }
}

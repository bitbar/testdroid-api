package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class APIAccount extends APIEntity {

    private String comment;

    private Date createTime;

    private String name;

    private String userName;

    @JsonInclude(Include.NON_NULL)
    private String activeServiceName;

    @JsonInclude(Include.NON_NULL)
    private Long dedicatedDevicesCount;

    public APIAccount() {
    }

    public APIAccount(Long id, String name, String userName, String comment, LocalDateTime createTime) {
        super(id);
        this.name = name;
        this.userName = userName;
        this.comment = comment;
        this.createTime = TimeConverter.toDate(createTime);
    }

    public APIAccount(
            Long id, String name, String userName, String comment, LocalDateTime createTime, long dedicatedDevicesCount,
            String activeServiceName) {
        this(id, name, userName, comment, createTime);
        this.dedicatedDevicesCount = dedicatedDevicesCount;
        this.activeServiceName = activeServiceName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActiveServiceName() {
        return activeServiceName;
    }

    public void setActiveServiceName(String activeServiceName) {
        this.activeServiceName = activeServiceName;
    }

    public Long getDedicatedDevicesCount() {
        return dedicatedDevicesCount;
    }

    public void setDedicatedDevicesCount(Long dedicatedDevicesCount) {
        this.dedicatedDevicesCount = dedicatedDevicesCount;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAccount account = (APIAccount) from;
        cloneBase(from);
        this.createTime = account.createTime;
        this.activeServiceName = account.activeServiceName;
        this.dedicatedDevicesCount = account.dedicatedDevicesCount;
        this.comment = account.comment;
        this.name = account.name;
        this.userName = account.userName;
    }
}

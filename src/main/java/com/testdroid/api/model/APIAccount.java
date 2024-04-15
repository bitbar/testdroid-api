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

    private Long mainUserId;

    private String mainUserFirstName;

    private String mainUserLastName;

    private String mainUserEmail;

    @JsonInclude(Include.NON_NULL)
    private String activeServiceName;

    @JsonInclude(Include.NON_NULL)
    private Long dedicatedDevicesCount;

    public APIAccount() {
    }

    public APIAccount(
            Long id, String comment, LocalDateTime createTime, Long mainUserId, String mainUserFirstName,
            String mainUserLastName, String mainUserEmail) {
        super(id);
        this.comment = comment;
        this.createTime = TimeConverter.toDate(createTime);
        this.mainUserId = mainUserId;
        this.mainUserFirstName = mainUserFirstName;
        this.mainUserLastName = mainUserLastName;
        this.mainUserEmail = mainUserEmail;
    }

    @SuppressWarnings("squid:S107")
    public APIAccount(
            Long id, String comment, LocalDateTime createTime, Long mainUserId, String mainUserFirstName,
            String mainUserLastName, String mainUserEmail, long dedicatedDevicesCount, String activeServiceName) {
        this(id, comment, createTime, mainUserId, mainUserFirstName, mainUserLastName, mainUserEmail);
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

    public Long getMainUserId() {
        return mainUserId;
    }

    public void setMainUserId(Long mainUserId) {
        this.mainUserId = mainUserId;
    }

    public String getMainUserFirstName() {
        return mainUserFirstName;
    }

    public void setMainUserFirstName(String mainUserFirstName) {
        this.mainUserFirstName = mainUserFirstName;
    }

    public String getMainUserLastName() {
        return mainUserLastName;
    }

    public void setMainUserLastName(String mainUserLastName) {
        this.mainUserLastName = mainUserLastName;
    }

    public String getMainUserEmail() {
        return mainUserEmail;
    }

    public void setMainUserEmail(String mainUserEmail) {
        this.mainUserEmail = mainUserEmail;
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
        this.mainUserId = account.mainUserId;
        this.createTime = account.createTime;
        this.mainUserFirstName = account.mainUserFirstName;
        this.mainUserLastName = account.mainUserLastName;
        this.mainUserEmail = account.mainUserEmail;
        this.activeServiceName = account.activeServiceName;
        this.dedicatedDevicesCount = account.dedicatedDevicesCount;
        this.comment = account.comment;
    }
}

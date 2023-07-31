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

    private Date createTime;

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
            Long id, LocalDateTime createTime, String mainUserFirstName, String mainUserLastName,
            String mainUserEmail) {
        super(id);
        this.createTime = TimeConverter.toDate(createTime);
        this.mainUserFirstName = mainUserFirstName;
        this.mainUserLastName = mainUserLastName;
        this.mainUserEmail = mainUserEmail;
    }

    public APIAccount(
            Long id, LocalDateTime createTime, String mainUserFirstName, String mainUserLastName, String mainUserEmail,
            long dedicatedDevicesCount) {
        this(id, createTime, mainUserFirstName, mainUserLastName, mainUserEmail);
        this.dedicatedDevicesCount = dedicatedDevicesCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        this.createTime = account.createTime;
        this.mainUserFirstName = account.mainUserFirstName;
        this.mainUserLastName = account.mainUserLastName;
        this.mainUserEmail = account.mainUserEmail;
        this.activeServiceName = account.activeServiceName;
        this.dedicatedDevicesCount = account.dedicatedDevicesCount;
    }
}

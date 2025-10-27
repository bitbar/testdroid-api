package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDateTime;
import java.util.Date;

import static com.testdroid.api.dto.MappingKey.ID;
import static com.testdroid.api.dto.MappingKey.SELF_URI;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@JsonIgnoreProperties(value = {ID, SELF_URI})
public class APIDeviceSessionUsage extends APIEntity {

    @XmlType(namespace = "APIDeviceSessionUsage")
    public enum Type {
        AUTOMATIC,
        MANUAL,
        DEDICATED_AUTOMATIC,
        DEDICATED_MANUAL,
        REPORT
    }

    private Date createTime;

    private String userName;

    private Long userId;

    private Long duration;

    private APIDeviceSessionUsage.Type type;

    private String deviceModelName;

    private String osVersion;

    public APIDeviceSessionUsage() {

    }

    public APIDeviceSessionUsage(
            LocalDateTime createTime, String userName, Long userId, Long duration,
            APIDeviceSessionUsage.Type type, String deviceModelName, String osVersion) {
        this.createTime = TimeConverter.toDate(createTime);
        this.userName = userName;
        this.userId = userId;
        this.duration = duration;
        this.type = type;
        this.deviceModelName = deviceModelName;
        this.osVersion = osVersion;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceSessionUsage original = (APIDeviceSessionUsage) from;
        cloneBase(from);
        this.createTime = original.createTime;
        this.userName = original.userName;
        this.userId = original.userId;
        this.duration = original.duration;
        this.type = original.type;
        this.deviceModelName = original.deviceModelName;
        this.osVersion = original.osVersion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public APIDeviceSessionUsage.Type getType() {
        return type;
    }

    public void setType(APIDeviceSessionUsage.Type type) {
        this.type = type;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getDeviceModelName() {
        return deviceModelName;
    }

    public void setDeviceModelName(String deviceModelName) {
        this.deviceModelName = deviceModelName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
}

package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
@XmlRootElement
public class APIDeviceTimeEntry extends APIEntity {

    private Date startTime;

    private Date endTime;

    private String userName;

    private Long userId;

    private Long deviceTime;

    private APIDeviceSession.Type type;

    public APIDeviceTimeEntry() {

    }

    public APIDeviceTimeEntry(
            Date startTime, Date endTime, String userName, Long userId, Long deviceTime,
            APIDeviceSession.Type type) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.userName = userName;
        this.userId = userId;
        this.deviceTime = deviceTime;
        this.type = type;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceTimeEntry apiDeviceTimeEntry = (APIDeviceTimeEntry) from;
        cloneBase(from);
        this.startTime = apiDeviceTimeEntry.startTime;
        this.endTime = apiDeviceTimeEntry.endTime;
        this.userName = apiDeviceTimeEntry.userName;
        this.userId = apiDeviceTimeEntry.userId;
        this.deviceTime = apiDeviceTimeEntry.deviceTime;
        this.type = apiDeviceTimeEntry.type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Long getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Long deviceTime) {
        this.deviceTime = deviceTime;
    }

    public APIDeviceSession.Type getType() {
        return type;
    }

    public void setType(APIDeviceSession.Type type) {
        this.type = type;
    }
}

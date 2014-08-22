package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * @author Jarno Tuovinen <jarno.tuovinen@bitbar.com>
 */
@XmlRootElement
public class APIConnection extends APIEntity {

    @XmlType
    public static enum Type {
        VNC,
        LOGS
    }

    private Date createTime;

    private Date endTime;

    private Long deviceSessionId;

    private String password;

    private APIConnection.Type type;

    private String url;

    public APIConnection() {
    }

    public APIConnection(
            Long id, Date createTime, Date endTime, Long deviceSessionId, String password, Type type,
            String url) {
        super(id);
        this.createTime = createTime;
        this.endTime = endTime;
        this.deviceSessionId = deviceSessionId;
        this.password = password;
        this.type = type;
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDeviceSessionId() {
        return deviceSessionId;
    }

    public void setDeviceSessionId(Long deviceSessionId) {
        this.deviceSessionId = deviceSessionId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIConnection apiConnection = (APIConnection) from;
        cloneBase(from);
        this.createTime = apiConnection.createTime;
        this.endTime = apiConnection.endTime;
        this.password = apiConnection.password;
        this.type = apiConnection.type;
        this.url = apiConnection.url;
    }

}

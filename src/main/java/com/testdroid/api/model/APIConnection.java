package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Jarno Tuovinen <jarno.tuovinen@bitbar.com>
 */
@XmlRootElement
public class APIConnection extends APIEntity {

    private Date createTime;

    private Date endTime;

    private Long deviceSessionId;

    private String password;

    private String type;

    @Deprecated
    private String url;

    private Integer port;

    private String path;

    private String urlSchema;

    private String host;

    public APIConnection() {
    }

    public APIConnection(
            Long id, Date createTime, Date endTime, Long deviceSessionId, String password, String type,
            String urlSchema, String host, Integer port, String path) {
        super(id);
        this.createTime = createTime;
        this.endTime = endTime;
        this.deviceSessionId = deviceSessionId;
        this.password = password;
        this.type = type;
        this.port = port;
        this.path = path;
        this.urlSchema = urlSchema;
        this.host = host;
        this.url = String.format("%s:%d", host, port);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlSchema() {
        return urlSchema;
    }

    public void setUrlSchema(String urlSchema) {
        this.urlSchema = urlSchema;
    }

    public String getPath() {
        return path;
    }

    public Integer getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setPath(String path) {
        this.path = path;
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
        this.port = apiConnection.port;
        this.path = apiConnection.path;
        this.urlSchema = apiConnection.urlSchema;
        this.host = apiConnection.host;
    }

}

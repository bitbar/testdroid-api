package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Jarno Tuovinen <jarno.tuovinen@bitbar.com>
 */
@XmlRootElement
public class APIConnection extends APIEntity {

    protected Date createTime;

    protected String password;

    protected String type;

    protected String url;

    public APIConnection() {
    }

    public APIConnection(Long id, Date createTime, String password, String type, String url) {
        super(id);
        this.url = url;
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIConnection apiConnection = (APIConnection) from;
        cloneBase(from);
        this.createTime = apiConnection.createTime;
        this.password = apiConnection.password;
        this.type = apiConnection.type;
        this.url = apiConnection.url;
    }

}

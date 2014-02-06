package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIDeviceSession extends APIEntity {

    @XmlType(namespace = "APIDeviceSession")
    public static enum Type {
        INSPECTOR
    }
    
    private Type type;
    private Date createTime;

    public APIDeviceSession() {
    }

    public APIDeviceSession(Long id, Type type, Date createTime) {
        super(id);
        this.type = type;
        this.createTime = createTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceSession apiDeviceSession = (APIDeviceSession) from;
        cloneBase(from);
        this.createTime = apiDeviceSession.createTime;
        this.type = apiDeviceSession.type;
    }

}

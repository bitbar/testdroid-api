package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author kajdus
 */
@XmlRootElement
@JsonIgnoreProperties(value = {"id"})
public class APIRecorderOnlineSession extends APIEntity {
    private String screenplayContent;
    private Date startTime;
    private Date updateTime;
    private Date endTime;
    private String sessionKey;
    
    @XmlType
    public static enum Action { UPDATE, CLOSE }

    public APIRecorderOnlineSession() {}

    public APIRecorderOnlineSession(Long id, String screenplayContent, Date startTime, Date endTime, Date updateTime, String sessionKey) {
        super(id);
        this.screenplayContent = screenplayContent;
        this.startTime = startTime;
        this.updateTime = updateTime;
        this.endTime = endTime;
        this.sessionKey = sessionKey;
    }

    public String getScreenplayContent() {
        return screenplayContent;
    }

    public void setScreenplayContent(String screenplayContent) {
        this.screenplayContent = screenplayContent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIRecorderOnlineSession apiRecorderOnlineSession = (APIRecorderOnlineSession) from;
        cloneBase(from);
        this.endTime = apiRecorderOnlineSession.endTime;
        this.screenplayContent = apiRecorderOnlineSession.screenplayContent;
        this.sessionKey = apiRecorderOnlineSession.sessionKey;
        this.startTime = apiRecorderOnlineSession.startTime;
        this.updateTime = apiRecorderOnlineSession.updateTime;
    }
    
}

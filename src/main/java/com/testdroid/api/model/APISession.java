package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APISession extends APIEntity {

    private String headers;

    private String ip;

    private Date loginTime;

    private Date logoutTime;

    private String sessionId;

    private Long userId;

    private String email;

    public APISession() {
    }

    public APISession(
            Long id, String sessionId, Date loginTime, Date logoutTime, String ip, String headers, Long userId,
            String email) {
        super(id);
        this.sessionId = sessionId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.ip = ip;
        this.headers = headers;
        this.userId = userId;
        this.email = email;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void close() throws APIException {
        APISession session = postResource(selfURI, "", APISession.class);
        this.sessionId = session.sessionId;
        this.loginTime = session.loginTime;
        this.logoutTime = session.logoutTime;
        this.ip = session.ip;
        this.headers = session.headers;
        this.userId = session.userId;
        this.email = session.email;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APISession apiSession = (APISession) from;
        cloneBase(from);
        this.headers = apiSession.headers;
        this.ip = apiSession.ip;
        this.loginTime = apiSession.loginTime;
        this.logoutTime = apiSession.logoutTime;
        this.sessionId = apiSession.sessionId;
    }

}

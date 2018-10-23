package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIAdminEmail extends APIEntity {

    private Date createTime;

    private Date lastRetryTime;

    private Boolean sent;

    private String subject;

    private String userEmail;

    private Long userId;

    public APIAdminEmail() {
    }

    public APIAdminEmail(
            Long id, Long userId, String userEmail, String subject, LocalDateTime createTime,
            LocalDateTime lastRetryTime, Boolean sent) {
        super(id);
        this.userId = userId;
        this.userEmail = userEmail;
        this.subject = subject;
        this.createTime = TimeConverter.toDate(createTime);
        this.lastRetryTime = TimeConverter.toDate(lastRetryTime);
        this.sent = sent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastRetryTime() {
        return lastRetryTime;
    }

    public void setLastRetryTime(Date lastRetryTime) {
        this.lastRetryTime = lastRetryTime;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAdminEmail email = (APIAdminEmail) from;
        cloneBase(from);
        this.userId = email.userId;
        this.userEmail = email.userEmail;
        this.subject = email.subject;
        this.createTime = email.createTime;
        this.lastRetryTime = email.lastRetryTime;
        this.sent = email.sent;
    }
}

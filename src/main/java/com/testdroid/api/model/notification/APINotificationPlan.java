package com.testdroid.api.model.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APINotificationPlan extends APIEntity {

    private String name;

    private Long handlerId;

    private String handlerEmail;

    private String contentTemplate;

    private String subjectTemplate;

    private Date createTime;

    private Date updateTime;

    private Date sentTime;

    private APINotificationChannel channel;

    private APINotificationScope scope;

    public APINotificationPlan() {

    }

    public APINotificationPlan(
            Long id, String name, Long handlerId, String handlerEmail, String contentTemplate, String subjectTemplate,
            LocalDateTime createTime, LocalDateTime updateTime, LocalDateTime sentTime,
            APINotificationChannel channel, APINotificationScope scope) {
        super(id);
        this.name = name;
        this.handlerId = handlerId;
        this.handlerEmail = handlerEmail;
        this.contentTemplate = contentTemplate;
        this.subjectTemplate = subjectTemplate;
        this.createTime = TimeConverter.toDate(createTime);
        this.updateTime = TimeConverter.toDate(updateTime);
        this.sentTime = TimeConverter.toDate(sentTime);
        this.channel = channel;
        this.scope = scope;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerEmail() {
        return handlerEmail;
    }

    public void setHandlerEmail(String handlerEmail) {
        this.handlerEmail = handlerEmail;
    }

    public String getContentTemplate() {
        return contentTemplate;
    }

    public void setContentTemplate(String contentTemplate) {
        this.contentTemplate = contentTemplate;
    }

    public String getSubjectTemplate() {
        return subjectTemplate;
    }

    public void setSubjectTemplate(String subjectTemplate) {
        this.subjectTemplate = subjectTemplate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public APINotificationChannel getChannel() {
        return channel;
    }

    public void setChannel(APINotificationChannel channel) {
        this.channel = channel;
    }

    public APINotificationScope getScope() {
        return scope;
    }

    public void setScope(APINotificationScope scope) {
        this.scope = scope;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APINotificationPlan notificationPlan = (APINotificationPlan) from;
        cloneBase(from);
        this.name = notificationPlan.name;
        this.handlerId = notificationPlan.handlerId;
        this.handlerEmail = notificationPlan.handlerEmail;
        this.contentTemplate = notificationPlan.contentTemplate;
        this.subjectTemplate = notificationPlan.subjectTemplate;
        this.createTime = notificationPlan.createTime;
        this.updateTime = notificationPlan.updateTime;
        this.sentTime = notificationPlan.sentTime;
        this.channel = notificationPlan.channel;
        this.scope = notificationPlan.scope;
    }

}

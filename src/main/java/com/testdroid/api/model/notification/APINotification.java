package com.testdroid.api.model.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APINotification extends APIEntity {

    private String destination;

    private Long userId;

    private String userEmail;

    private Long projectId;

    private String projectName;

    private APINotificationChannel notificationChannel;

    private APINotificationScope notificationScope;

    public APINotification() {
    }

    public APINotification(
            Long id, String destination, Long userId, String userEmail, Long projectId, String projectName,
            APINotificationChannel notificationChannel,
            APINotificationScope notificationScope) {
        super(id);
        this.destination = destination;
        this.userId = userId;
        this.userEmail = userEmail;
        this.projectId = projectId;
        this.projectName = projectName;
        this.notificationChannel = notificationChannel;
        this.notificationScope = notificationScope;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public APINotificationChannel getNotificationChannel() {
        return notificationChannel;
    }

    public void setNotificationChannel(APINotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public APINotificationScope getNotificationScope() {
        return notificationScope;
    }

    public void setNotificationScope(APINotificationScope notificationScope) {
        this.notificationScope = notificationScope;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APINotification apiNotification = (APINotification) from;
        cloneBase(from);
        this.destination = apiNotification.destination;
        this.notificationChannel = apiNotification.notificationChannel;
        this.notificationScope = apiNotification.notificationScope;
        this.projectId = apiNotification.projectId;
        this.userId = apiNotification.userId;
        this.userEmail = apiNotification.userEmail;
        this.projectName = apiNotification.projectName;
    }

}

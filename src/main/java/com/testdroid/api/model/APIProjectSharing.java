package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIProjectSharing extends APIEntity {

    private String userEmail;

    private Long userId;

    private Long accessGroupId;

    private Long resourceId;

    public APIProjectSharing() {
    }

    public APIProjectSharing(Long id, Long userId, String userEmail, Long accessGroupId, Long resourceId) {
        super(id);
        this.userId = userId;
        this.userEmail = userEmail;
        this.accessGroupId = accessGroupId;
        this.resourceId = resourceId;
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

    public Long getAccessGroupId() {
        return accessGroupId;
    }

    public void setAccessGroupId(Long accessGroupId) {
        this.accessGroupId = accessGroupId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIProjectSharing apiProjectSharing = (APIProjectSharing) from;
        cloneBase(from);
        this.userId = apiProjectSharing.userId;
        this.userEmail = apiProjectSharing.userEmail;
        this.accessGroupId = apiProjectSharing.accessGroupId;
        this.resourceId = apiProjectSharing.resourceId;
    }

}

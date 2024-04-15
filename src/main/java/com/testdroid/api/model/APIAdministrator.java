package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIAdministrator extends APIEntity {

    private String email;

    private APIUser.Status status;

    private Date createTime;

    private Date deleteTime;

    private boolean isAdmin;

    private boolean isAdminReadOnly;

    private boolean isUserAdmin;

    public APIAdministrator() {
    }

    @SuppressWarnings("squid:S107")
    public APIAdministrator(
            Long id, String email, APIUser.Status status, LocalDateTime createTime, LocalDateTime deleteTime,
            boolean isAdmin, boolean isAdminReadOnly, boolean isUserAdmin) {
        super(id);
        this.email = email;
        this.status = status;
        this.createTime = TimeConverter.toDate(createTime);
        this.deleteTime = TimeConverter.toDate(deleteTime);
        this.isAdmin = isAdmin;
        this.isAdminReadOnly = isAdminReadOnly;
        this.isUserAdmin = isUserAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public APIUser.Status getStatus() {
        return status;
    }

    public void setStatus(APIUser.Status status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    @JsonProperty("isAdmin")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @JsonProperty("isAdminReadOnly")
    public boolean isAdminReadOnly() {
        return isAdminReadOnly;
    }

    public void setAdminReadOnly(boolean adminReadOnly) {
        isAdminReadOnly = adminReadOnly;
    }

    @JsonProperty("isUserAdmin")
    public boolean isUserAdmin() {
        return isUserAdmin;
    }

    public void setUserAdmin(boolean userAdmin) {
        isUserAdmin = userAdmin;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAdministrator original = (APIAdministrator) from;
        cloneBase(original);
        this.email = original.email;
        this.status = original.status;
        this.createTime = original.createTime;
        this.deleteTime = original.deleteTime;
        this.isAdmin = original.isAdmin;
        this.isAdminReadOnly = original.isAdminReadOnly;
        this.isUserAdmin = original.isUserAdmin;
    }
}

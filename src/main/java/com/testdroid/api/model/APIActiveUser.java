package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIActiveUser extends APIEntity {

    private String country;

    private String email;

    private Date loginTime;

    private String organization;

    private Integer priorityDesktop;

    private Integer priorityMobile;

    private String timeZone;

    public APIActiveUser() {
    }

    public APIActiveUser(
            Long id, String country, String email, String organization, String timeZone, LocalDateTime loginTime) {
        super(id);
        this.country = country;
        this.email = email;
        this.organization = organization;
        this.timeZone = timeZone;
        this.loginTime = TimeConverter.toDate(loginTime);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getPriorityDesktop() {
        return priorityDesktop;
    }

    public void setPriorityDesktop(Integer priorityDesktop) {
        this.priorityDesktop = priorityDesktop;
    }

    public Integer getPriorityMobile() {
        return priorityMobile;
    }

    public void setPriorityMobile(Integer priorityMobile) {
        this.priorityMobile = priorityMobile;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIActiveUser apiActiveUser = (APIActiveUser) from;
        cloneBase(from);
        this.country = apiActiveUser.country;
        this.email = apiActiveUser.email;
        this.organization = apiActiveUser.organization;
        this.timeZone = apiActiveUser.timeZone;
        this.loginTime = apiActiveUser.loginTime;
        this.priorityDesktop = apiActiveUser.priorityDesktop;
        this.priorityMobile = apiActiveUser.priorityMobile;
    }
}

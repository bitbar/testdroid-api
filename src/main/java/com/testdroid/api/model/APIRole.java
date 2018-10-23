package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIRole extends APIEntity {

    private String addedByEmail;

    private Date expireTime;

    private String name;

    public APIRole() {
    }

    public APIRole(Long id, String name) {
        super(id);
        this.name = name;
    }

    public APIRole(Long id, String name, LocalDateTime expireTime) {
        this(id, name);
        this.expireTime = TimeConverter.toDate(expireTime);
    }

    public APIRole(Long id, String name, LocalDateTime expireTime, String addedByEmail) {
        this(id, name, expireTime);
        this.addedByEmail = addedByEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getAddedByEmail() {
        return addedByEmail;
    }

    public void setAddedByEmail(String addedByEmail) {
        this.addedByEmail = addedByEmail;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIRole apiRole = (APIRole) from;
        cloneBase(from);
        this.expireTime = apiRole.expireTime;
        this.name = apiRole.name;
        this.addedByEmail = apiRole.addedByEmail;
    }
}

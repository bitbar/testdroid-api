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

    private Integer value;

    private boolean valueCalculated;

    public APIRole() {
    }

    // FrameworkRole, Role
    public APIRole(Long id, String name) {
        super(id);
        this.name = name;
    }

    // ServiceRole
    public APIRole(Long id, String name, Integer value, boolean valueCalculated) {
        this(id, name);
        this.value = value;
        this.valueCalculated = valueCalculated;
    }

    // AccountRole
    public APIRole(Long id, String name, LocalDateTime expireTime, Integer value) {
        this(id, name, value, false);
        this.expireTime = TimeConverter.toDate(expireTime);
    }

    // AccountRole
    public APIRole(Long parentId, Long id, String name, LocalDateTime expireTime, Integer value) {
        this(id, name, value, false);
        this.expireTime = TimeConverter.toDate(expireTime);
        this.parentId = parentId;
    }

    // AdminAccountRole
    public APIRole(Long id, String name, LocalDateTime expireTime, String addedByEmail, Integer value) {
        this(id, name, expireTime, value);
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isValueCalculated() {
        return valueCalculated;
    }

    public void setValueCalculated(boolean valueCalculated) {
        this.valueCalculated = valueCalculated;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIRole apiRole = (APIRole) from;
        cloneBase(from);
        this.expireTime = apiRole.expireTime;
        this.name = apiRole.name;
        this.addedByEmail = apiRole.addedByEmail;
        this.value = apiRole.value;
        this.valueCalculated = apiRole.valueCalculated;
    }
}

package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
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
    public APIRole(Long parentId, Long id, String name, Integer value, boolean valueCalculated) {
        this(id, name);
        this.parentId = parentId;
        this.value = value;
        this.valueCalculated = valueCalculated;
    }

    // AccountRole
    public APIRole(Long id, String name, LocalDateTime expireTime, Integer value) {
        this(null, id, name, value, false);
        this.expireTime = TimeConverter.toDate(expireTime);
    }

    // AccountRole & AdminAccountRole
    public APIRole(Long parentId, Long id, String name, LocalDateTime expireTime, String addedByEmail, Integer value) {
        this(parentId, id, name, value, false);
        this.addedByEmail = addedByEmail;
        this.expireTime = TimeConverter.toDate(expireTime);
    }

    // UserRole
    public APIRole(Long parentId, Long id, String name, String addedByEmail) {
        this(id, name);
        this.parentId = parentId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        APIRole apiRole = (APIRole) o;
        return valueCalculated == apiRole.valueCalculated &&
                Objects.equals(addedByEmail, apiRole.addedByEmail) &&
                Objects.equals(expireTime, apiRole.expireTime) &&
                name.equals(apiRole.name) &&
                Objects.equals(value, apiRole.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addedByEmail, expireTime, name, value, valueCalculated);
    }
}

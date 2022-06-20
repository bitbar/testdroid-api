package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIProjectJobConfig extends APIEntity {

    private String content;

    private Long frameworkId;

    private boolean global;

    private Date lastModificationTime;

    private String lastModifiedBy;

    private Integer version;

    public APIProjectJobConfig() {
    }

    public APIProjectJobConfig(
            Long id, String content, Integer version, boolean global,
            LocalDateTime lastModificationTime, String lastModifiedBy, Long frameworkId) {
        super(id);
        this.content = content;
        this.version = version;
        this.global = global;
        this.lastModificationTime = TimeConverter.toDate(lastModificationTime);
        this.lastModifiedBy = lastModifiedBy;
        this.frameworkId = frameworkId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public Long getFrameworkId() {
        return frameworkId;
    }

    public void setFrameworkId(Long frameworkId) {
        this.frameworkId = frameworkId;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIProjectJobConfig apiProjectJobConfig = (APIProjectJobConfig) from;
        cloneBase(from);
        this.content = apiProjectJobConfig.content;
        this.version = apiProjectJobConfig.version;
        this.global = apiProjectJobConfig.global;
        this.lastModificationTime = apiProjectJobConfig.lastModificationTime;
        this.lastModifiedBy = apiProjectJobConfig.lastModifiedBy;
        this.frameworkId = apiProjectJobConfig.frameworkId;
    }
}

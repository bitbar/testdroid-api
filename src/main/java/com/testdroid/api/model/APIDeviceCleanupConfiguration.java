package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIDeviceCleanupConfiguration extends APIEntity {

    private String content;

    private Date createTime;

    private String createdByEmail;

    private Long createdById;

    private APIDevice.OsType osType = APIDevice.OsType.ANDROID;

    private Date lastModificationTime;

    private Boolean enabled;

    private Boolean global;

    private String discriminator;

    public APIDeviceCleanupConfiguration() {
    }

    @SuppressWarnings("squid:S107")
    public APIDeviceCleanupConfiguration(
            Long id, String content, Boolean enabled, LocalDateTime createTime, String createdByEmail, Long createdById,
            APIDevice.OsType osType, LocalDateTime lastModificationTime, String discriminator, Boolean global) {
        super(id);
        this.content = content;
        this.enabled = enabled;
        this.createTime = TimeConverter.toDate(createTime);
        this.createdByEmail = createdByEmail;
        this.createdById = createdById;
        this.osType = osType;
        this.lastModificationTime = TimeConverter.toDate(lastModificationTime);
        this.global = global;
        this.discriminator = discriminator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        this.createdByEmail = createdByEmail;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public APIDevice.OsType getOsType() {
        return osType;
    }

    public void setOsType(APIDevice.OsType osType) {
        this.osType = osType;
    }

    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    @Override
    @JsonIgnore
    public <T extends APIEntity> void clone(T from) {
        APIDeviceCleanupConfiguration deviceCleanupConfiguration = (APIDeviceCleanupConfiguration) from;
        cloneBase(from);
        this.createTime = deviceCleanupConfiguration.createTime;
        this.content = deviceCleanupConfiguration.content;
        this.enabled = deviceCleanupConfiguration.enabled;
        this.osType = deviceCleanupConfiguration.osType;
        this.createdByEmail = deviceCleanupConfiguration.createdByEmail;
        this.createdById = deviceCleanupConfiguration.createdById;
        this.lastModificationTime = deviceCleanupConfiguration.lastModificationTime;
        this.global = deviceCleanupConfiguration.global;
        this.discriminator = deviceCleanupConfiguration.discriminator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        APIDeviceCleanupConfiguration that = (APIDeviceCleanupConfiguration) o;
        return Objects.equals(content, that.content) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createdByEmail, that.createdByEmail) &&
                Objects.equals(createdById, that.createdById) &&
                osType == that.osType &&
                Objects.equals(lastModificationTime, that.lastModificationTime) &&
                Objects.equals(enabled, that.enabled) &&
                Objects.equals(global, that.global) &&
                Objects.equals(discriminator, that.discriminator);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(content, createTime, createdByEmail, createdById, osType, lastModificationTime, enabled,
                        global, discriminator);
    }
}

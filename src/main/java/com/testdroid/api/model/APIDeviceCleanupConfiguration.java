package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIDeviceCleanupConfiguration extends APIEntity {

    private String content;

    private Date createTime;

    private String createdByEmail;

    private Long createdById;

    private APIDevice.OsType osType = APIDevice.OsType.ANDROID;

    private Date lastModificationTime;

    private Boolean enabled;

    public APIDeviceCleanupConfiguration() {
    }

    public APIDeviceCleanupConfiguration(
            Long id, String content, Boolean enabled, LocalDateTime createTime, String createdByEmail, Long createdById,
            APIDevice.OsType osType, LocalDateTime lastModificationTime) {
        super(id);
        this.content = content;
        this.enabled = enabled;
        this.createTime = TimeConverter.toDate(createTime);
        this.createdByEmail = createdByEmail;
        this.createdById = createdById;
        this.osType = osType;
        this.lastModificationTime = TimeConverter.toDate(lastModificationTime);
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

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceCleanupConfiguration deviceCleanupConfiguration = (APIDeviceCleanupConfiguration) from;
        cloneBase(from);
        this.createTime = deviceCleanupConfiguration.createTime;
        this.content = deviceCleanupConfiguration.content;
        this.enabled = deviceCleanupConfiguration.enabled;
        this.osType = deviceCleanupConfiguration.osType;
        this.createdByEmail = deviceCleanupConfiguration.createdByEmail;
        this.createdById = deviceCleanupConfiguration.createdById;
        this.lastModificationTime = deviceCleanupConfiguration.lastModificationTime;
    }
}

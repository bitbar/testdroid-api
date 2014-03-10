package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@XmlRootElement
public class APIProjectJobConfig extends APIEntity {
    
    @XmlType(namespace = "APIProjectJobConfig")
    public static enum Type { DEFAULT, RESIGNING, INSTATEST, CTS, IOS, UIAUTOMATOR, REMOTECONTROL,
        CALABASH, CALABASH_IOS, RECORDERONLINE, APPIUM_ANDROID, APPIUM_IOS}

    private Type type;
    private String content;
    private Integer version;
    private boolean global;
    private Long projectId;
    private String projectName;
    private String lastModifiedBy;
    private Date lastModificationTime;

    public APIProjectJobConfig() {}

    public APIProjectJobConfig(Long id, Type type, String content, Integer version, boolean global, Long projectId, 
                String projectName, Date lastModificationTime, String lastModifiedBy) {
        super(id);
        this.type = type;
        this.content = content;
        this.version = version;
        this.global = global;
        this.projectId = projectId;
        this.projectName = projectName;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModificationTime = lastModificationTime;
    }
    
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
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

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIProjectJobConfig apiProjectJobConfig = (APIProjectJobConfig) from;
        cloneBase(from);
        this.content = apiProjectJobConfig.content;
        this.global = apiProjectJobConfig.global;
        this.projectId = apiProjectJobConfig.projectId;
        this.type = apiProjectJobConfig.type;
        this.version = apiProjectJobConfig.version;
        this.projectName = apiProjectJobConfig.projectName;
        this.lastModificationTime = apiProjectJobConfig.lastModificationTime;
        this.lastModifiedBy = apiProjectJobConfig.lastModifiedBy;
    }
}

package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APIProjectJobConfig extends APIEntity {
    @XmlType(name = "jobConfigType")
    public static enum Type { DEFAULT, RESIGNING, INSTATEST, CTS, IOS, UIAUTOMATOR, REMOTECONTROL, CALABASH }

    private Type type;
    private String content;
    private Integer version;
    private boolean global;
    private Long projectId;

    public APIProjectJobConfig() {}

    public APIProjectJobConfig(Long id, Type type, String content, Integer version, boolean global, Long projectId) {
        super(id);
        this.type = type;
        this.content = content;
        this.version = version;
        this.global = global;
        this.projectId = projectId;
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
    }
}

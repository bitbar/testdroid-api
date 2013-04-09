package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author kajdus
 */
@XmlRootElement
public class APIProjectJobConfig extends APIEntity {
    @XmlType(name = "jobConfigType")
    public static enum Type { DEFAULT, RESIGNING, INSTATEST, CTS, IOS, UIAUTOMATOR, REMOTECONTROL }
    
    private Type type;
    private String content;
    private Integer version;
    private boolean global;

    public APIProjectJobConfig() {}

    public APIProjectJobConfig(Long id, Type type, String content, Integer version, boolean global) {
        super(id);
        this.type = type;
        this.content = content;
        this.version = version;
        this.global = global;
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

}

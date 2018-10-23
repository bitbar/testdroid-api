package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIUserIntegration extends APIEntity {

    @XmlType(namespace = "APIUserIntegration")
    public enum Type {
        GAMEBENCH,
        JIRA,
        CUSTOM
    }

    private String apiKey;

    private Date createTime;

    private Boolean hasCert;

    private Type type;

    private String url;

    private Long userId;

    private String username;

    public APIUserIntegration() {
    }

    public APIUserIntegration(
            Long id, Long userId, LocalDateTime createTime, String apiKey, String username, Type type, String url,
            boolean hasCert) {
        super(id);
        this.userId = userId;
        this.createTime = TimeConverter.toDate(createTime);
        this.apiKey = apiKey;
        this.username = username;
        this.type = type;
        this.url = url;
        this.hasCert = hasCert;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getHasCert() {
        return hasCert;
    }

    public void setHasCert(Boolean hasCert) {
        this.hasCert = hasCert;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIUserIntegration apiUserIntegration = (APIUserIntegration) from;
        cloneBase(from);
        this.userId = apiUserIntegration.userId;
        this.createTime = apiUserIntegration.createTime;
        this.apiKey = apiUserIntegration.apiKey;
        this.username = apiUserIntegration.username;
        this.type = apiUserIntegration.type;
        this.hasCert = apiUserIntegration.hasCert;
        this.url = apiUserIntegration.url;
    }
}

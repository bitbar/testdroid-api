package com.testdroid.api.jrjc.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIBasicJiraAccount", namespace = "cloud.testdroid.api.jira.model")
@XmlType(name = "APIBasicJiraAccount", namespace = "cloud.testdroid.api.jira.model")
public class APIBasicJiraAccount extends APIEntity {

    private String jiraUrl;

    private Long userId;

    private String username;

    public APIBasicJiraAccount() {
    }

    public APIBasicJiraAccount(Long id, String username, String jiraUrl, Long userId) {
        super(id);
        this.userId = userId;
        this.username = username;
        this.jiraUrl = jiraUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJiraUrl() {
        return jiraUrl;
    }

    public void setJiraUrl(String jiraUrl) {
        this.jiraUrl = jiraUrl;
    }

    @Override protected <T extends APIEntity> void clone(T from) {
        cloneBase(from);
        APIBasicJiraAccount account = (APIBasicJiraAccount) from;
        this.userId = account.userId;
        this.username = account.username;
        this.jiraUrl = account.jiraUrl;
    }
}

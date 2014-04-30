package com.testdroid.api.jrjc.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author damian.sniezek<damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIBasicJiraAccount", namespace = "cloud.testdroid.api.jira.model")
@XmlType(name= "APIBasicJiraAccount", namespace = "cloud.testdroid.api.jira.model")
public class APIBasicJiraAccount {
    private Long id;

    private Long userId;

    private String username;

    private String jiraUrl;

    public APIBasicJiraAccount() {}

    public APIBasicJiraAccount(Long id, String username, String jiraUrl, Long userId) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.jiraUrl = jiraUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

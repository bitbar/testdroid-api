package com.testdroid.api.jrjc.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIBasicJiraIssue", namespace = "cloud.testdroid.api.jira.model")
@XmlType(name = "APIBasicJiraIssue", namespace = "cloud.testdroid.api.jira.model")
public class APIBasicJiraIssue extends APIEntity {

    private String key;

    private URI self;

    public APIBasicJiraIssue() {

    }

    public APIBasicJiraIssue(URI self, String key, Long id) {
        this.self = self;
        this.key = key;
        this.id = id;
    }

    /**
     * @return URI of this issue
     */
    public URI getSelf() {
        return self;
    }

    /**
     * @return issue key
     */
    public String getKey() {
        return key;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        cloneBase(from);
        APIBasicJiraIssue jiraIssueType = (APIBasicJiraIssue) from;
        this.self = jiraIssueType.self;
        this.key = jiraIssueType.key;
    }

}

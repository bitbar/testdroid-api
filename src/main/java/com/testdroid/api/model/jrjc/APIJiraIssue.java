package com.testdroid.api.model.jrjc;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIJiraIssue", namespace = "cloud.testdroid.api.model.jrjc")
@XmlType(name = "APIJiraIssue", namespace = "cloud.testdroid.api.model.jrjc")
public class APIJiraIssue extends APIEntity {

    private String key;

    private URI self;

    public APIJiraIssue() {
    }

    public APIJiraIssue(URI self, String key, Long id) {
        this.self = self;
        this.key = key;
        this.id = id;
    }

    public URI getSelf() {
        return self;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSelf(URI self) {
        this.self = self;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        cloneBase(from);
        APIJiraIssue jiraIssueType = (APIJiraIssue) from;
        this.self = jiraIssueType.self;
        this.key = jiraIssueType.key;
    }
}

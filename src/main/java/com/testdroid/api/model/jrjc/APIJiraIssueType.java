package com.testdroid.api.model.jrjc;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIJiraIssueType", namespace = "cloud.testdroid.api.model.jrjc")
@XmlType(name = "APIJiraIssueType", namespace = "cloud.testdroid.api.model.jrjc")
public class APIJiraIssueType extends APIEntity {

    private String name;

    public APIJiraIssueType() {
    }

    public APIJiraIssueType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        cloneBase(from);
        APIJiraIssueType jiraIssueType = (APIJiraIssueType) from;
        this.name = jiraIssueType.name;
    }
}

package com.testdroid.api.jrjc.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author damian.sniezek<damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIJiraIssueType", namespace = "cloud.testdroid.api.jira.model")
@XmlType(name="APIJiraIssueType", namespace = "cloud.testdroid.api.jira.model")
public class APIJiraIssueType extends APIEntity {

    private Long id;

    private String name;

    public APIJiraIssueType() {
    }

    public APIJiraIssueType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    protected <T extends APIEntity> void clone(T from) {

    }
}

package com.testdroid.api.model.jrjc;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIBasicJiraProject", namespace = "cloud.testdroid.api.model.jrjc")
@XmlType(name = "APIBasicJiraProject", namespace = "cloud.testdroid.api.model.jrjc")
public class APIBasicJiraProject extends APIEntity {

    private String key;

    private String name;

    private URI self;

    public APIBasicJiraProject() {

    }

    public APIBasicJiraProject(URI self, String key, String name) {
        this.self = self;
        this.key = key;
        this.name = name;
    }

    /**
     * @return URI of this project
     */
    public URI getSelf() {
        return self;
    }

    /**
     * @return project key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return project name
     */
    public String getName() {
        return name;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        cloneBase(from);
        APIBasicJiraProject jiraProject = (APIBasicJiraProject) from;
        this.self = jiraProject.self;
        this.key = jiraProject.key;
        this.name = jiraProject.name;
    }
}

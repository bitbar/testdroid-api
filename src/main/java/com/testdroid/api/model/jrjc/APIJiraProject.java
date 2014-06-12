package com.testdroid.api.model.jrjc;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIJiraProject", namespace = "cloud.testdroid.api.model.jrjc")
@XmlType(name = "APIJiraProject", namespace = "cloud.testdroid.api.model.jrjc")
public class APIJiraProject extends APIBasicJiraProject {

    private String description;

    private String leadName;


    public APIJiraProject() {
    }

    public APIJiraProject(URI self, String key, String name, String description, String leadName) {
        super(self, key, name);
        this.description = description;
        this.leadName = leadName;
    }

    public String getDescription() {
        return description;
    }

    public String getLeadName() {
        return leadName;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        super.clone(from);
        APIJiraProject jiraProject = (APIJiraProject) from;
        this.description = jiraProject.description;
        this.leadName = jiraProject.leadName;
    }
}

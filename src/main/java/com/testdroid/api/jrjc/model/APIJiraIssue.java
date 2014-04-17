package com.testdroid.api.jrjc.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;

/**
 *
 * @author damian.sniezek<damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIJiraIssue", namespace = "cloud.testdroid.api.jira.model")
@XmlType(name="APIJiraIssue", namespace = "cloud.testdroid.api.jira.model")
public class APIJiraIssue extends APIBasicJiraIssue{

    public APIJiraIssue() {
    }

    public APIJiraIssue(URI self, String key, Long id) {
        super(self, key, id);
    }
}

package com.testdroid.api.model.jrjc;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIJiraIssue", namespace = "cloud.testdroid.api.model.jrjc")
@XmlType(name = "APIJiraIssue", namespace = "cloud.testdroid.api.model.jrjc")
public class APIJiraIssue extends APIBasicJiraIssue {

    public APIJiraIssue() {
    }

    public APIJiraIssue(URI self, String key, Long id) {
        super(self, key, id);
    }
}

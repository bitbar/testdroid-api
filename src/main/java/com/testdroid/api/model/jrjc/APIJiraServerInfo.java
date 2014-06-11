package com.testdroid.api.model.jrjc;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement(name = "APIJiraServerInfo", namespace = "cloud.testdroid.api.model.jrjc")
@XmlType(name = "APIJiraServerInfo", namespace = "cloud.testdroid.api.model.jrjc")
public class APIJiraServerInfo extends APIEntity {

    private URI baseUri;

    private String version;

    public APIJiraServerInfo() {

    }

    public APIJiraServerInfo(URI baseUri, String version) {
        this.baseUri = baseUri;
        this.version = version;
    }

    public URI getBaseUri() {
        return baseUri;
    }

    public String getVersion() {
        return version;
    }

    public void setBaseUri(URI baseUri) {
        this.baseUri = baseUri;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        cloneBase(from);
        APIJiraServerInfo serverInfo = (APIJiraServerInfo) from;
        this.baseUri = serverInfo.baseUri;
        this.version = serverInfo.version;
    }
}


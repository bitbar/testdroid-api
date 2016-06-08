package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APICloudInfo extends APIEntity {

    private String cloudUrl;

    private String frontendUrl;

    private String version;

    public APICloudInfo() {
    }

    public APICloudInfo(String version, String frontendUrl, String cloudUrl) {
        this.version = version;
        this.frontendUrl = frontendUrl;
        this.cloudUrl = cloudUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFrontendUrl() {
        return frontendUrl;
    }

    public void setFrontendUrl(String frontendUrl) {
        this.frontendUrl = frontendUrl;
    }

    public String getCloudUrl() {
        return cloudUrl;
    }

    public void setCloudUrl(String cloudUrl) {
        this.cloudUrl = cloudUrl;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APICloudInfo apiUserFileTag = (APICloudInfo) from;
        cloneBase(from);
        this.cloudUrl = apiUserFileTag.cloudUrl;
        this.frontendUrl = apiUserFileTag.frontendUrl;
        this.version = apiUserFileTag.version;
    }
}

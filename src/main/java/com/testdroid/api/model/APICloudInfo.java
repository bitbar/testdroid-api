package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APICloudInfo extends APIEntity {

    private String cloudUrl;

    private String frontendUrl;

    private String version;

    private boolean licenseInstalled;

    public APICloudInfo() {
    }

    public APICloudInfo(String version, String frontendUrl, String cloudUrl, boolean licenseInstalled) {
        this.version = version;
        this.frontendUrl = frontendUrl;
        this.cloudUrl = cloudUrl;
        this.licenseInstalled = licenseInstalled;
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

    public boolean isLicenseInstalled() {
        return licenseInstalled;
    }

    public void setLicenseInstalled(boolean licenseInstalled) {
        this.licenseInstalled = licenseInstalled;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APICloudInfo original = (APICloudInfo) from;
        cloneBase(from);
        this.cloudUrl = original.cloudUrl;
        this.frontendUrl = original.frontendUrl;
        this.version = original.version;
        this.licenseInstalled = original.licenseInstalled;
    }
}

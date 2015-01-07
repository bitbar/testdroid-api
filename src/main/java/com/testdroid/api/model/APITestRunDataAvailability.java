package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APITestRunDataAvailability extends APIEntity {

    private boolean application;

    private APIDeviceRunDataAvailability[] deviceRunDataAvailabilities;

    private boolean test;

    private Long testRunId;

    public APITestRunDataAvailability() {
    }

    public APITestRunDataAvailability(Long testRunId, boolean application, boolean test) {
        this.testRunId = testRunId;
        this.application = application;
        this.test = test;
    }

    public Long getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(Long testRunId) {
        this.testRunId = testRunId;
    }

    public boolean isApplication() {
        return application;
    }

    public void setApplication(boolean application) {
        this.application = application;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public APIDeviceRunDataAvailability[] getDeviceRunDataAvailabilities() {
        return deviceRunDataAvailabilities;
    }

    public void setDeviceRunDataAvailabilities(APIDeviceRunDataAvailability[] deviceRunDataAvailabilities) {
        this.deviceRunDataAvailabilities = deviceRunDataAvailabilities;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APITestRunDataAvailability apiTestRunDataAvailability = (APITestRunDataAvailability) from;
        cloneBase(from);
        this.application = apiTestRunDataAvailability.application;
        this.test = apiTestRunDataAvailability.test;
        this.deviceRunDataAvailabilities = apiTestRunDataAvailability.deviceRunDataAvailabilities;
    }
}

package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APITestRunDataAvailability extends APIEntity {

    private APIDeviceSessionDataAvailability[] deviceRunDataAvailabilities;

    private Long testRunId;

    public APITestRunDataAvailability() {
    }

    public APITestRunDataAvailability(Long testRunId, APIDeviceSessionDataAvailability[] deviceRunDataAvailabilities) {
        this.testRunId = testRunId;
        this.deviceRunDataAvailabilities = deviceRunDataAvailabilities;
    }

    public Long getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(Long testRunId) {
        this.testRunId = testRunId;
    }

    public APIDeviceSessionDataAvailability[] getDeviceRunDataAvailabilities() {
        return deviceRunDataAvailabilities;
    }

    public void setDeviceRunDataAvailabilities(APIDeviceSessionDataAvailability[] deviceRunDataAvailabilities) {
        this.deviceRunDataAvailabilities = deviceRunDataAvailabilities;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APITestRunDataAvailability apiTestRunDataAvailability = (APITestRunDataAvailability) from;
        cloneBase(from);
        this.deviceRunDataAvailabilities = apiTestRunDataAvailability.deviceRunDataAvailabilities;
    }
}

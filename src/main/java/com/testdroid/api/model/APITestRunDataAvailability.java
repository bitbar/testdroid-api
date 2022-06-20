package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import java.util.List;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APITestRunDataAvailability extends APIEntity {

    private List<APIDeviceSessionDataAvailability> deviceRunDataAvailabilities;

    private Long testRunId;

    public APITestRunDataAvailability() {
    }

    public APITestRunDataAvailability(Long testRunId,
            List<APIDeviceSessionDataAvailability> deviceRunDataAvailabilities) {
        this.testRunId = testRunId;
        this.deviceRunDataAvailabilities = deviceRunDataAvailabilities;
    }

    public Long getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(Long testRunId) {
        this.testRunId = testRunId;
    }

    public List<APIDeviceSessionDataAvailability> getDeviceRunDataAvailabilities() {
        return deviceRunDataAvailabilities;
    }

    public void setDeviceRunDataAvailabilities(List<APIDeviceSessionDataAvailability> deviceRunDataAvailabilities) {
        this.deviceRunDataAvailabilities = deviceRunDataAvailabilities;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APITestRunDataAvailability apiTestRunDataAvailability = (APITestRunDataAvailability) from;
        cloneBase(from);
        this.deviceRunDataAvailabilities = apiTestRunDataAvailability.deviceRunDataAvailabilities;
    }
}

package com.testdroid.api.model.devicetime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.testdroid.api.APIEntity;
import com.testdroid.api.model.APIDevice;

import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class APIDeviceTimeStepTimeReportEntry extends APIDeviceTimeReportEntry {

    private Long preparationTime;

    private Long waitingTime;

    public APIDeviceTimeStepTimeReportEntry() {

    }

    @SuppressWarnings("squid:S107")
    public APIDeviceTimeStepTimeReportEntry(
            Date day, Long deviceTime, Long userId, String userEmail, Long deviceModelId, String deviceModelName,
            String projectId, String projectName, APIDevice.OsType osType, Long preparationTime, Long waitingTime) {
        super(day, deviceTime, userId, userEmail, deviceModelId, deviceModelName, projectId, projectName, osType);
        this.preparationTime = preparationTime;
        this.waitingTime = waitingTime;
    }

    public Long getPreparationTime() {
        return preparationTime;
    }

    public Long getWaitingTime() {
        return waitingTime;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceTimeStepTimeReportEntry apiDeviceTimeReportEntry = (APIDeviceTimeStepTimeReportEntry) from;
        super.clone(from);
        this.preparationTime = apiDeviceTimeReportEntry.preparationTime;
        this.waitingTime = apiDeviceTimeReportEntry.waitingTime;
    }
}

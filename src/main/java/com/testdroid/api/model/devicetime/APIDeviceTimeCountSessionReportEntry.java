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
public class APIDeviceTimeCountSessionReportEntry extends APIDeviceTimeReportEntry {

    private Long countTestRuns;

    private Long countDeviceSessions;

    public APIDeviceTimeCountSessionReportEntry() {

    }

    public APIDeviceTimeCountSessionReportEntry(
            Date day,
            Long deviceTime, Long userId, String userEmail, Long deviceModelId, String deviceModelName,
            String projectId, String projectName, APIDevice.OsType osType, Long countTestRuns,
            Long countDeviceSessions) {
        super(day, deviceTime, userId, userEmail, deviceModelId, deviceModelName, projectId, projectName, osType);
        this.countTestRuns = countTestRuns;
        this.countDeviceSessions = countDeviceSessions;
    }

    public Long getCountTestRuns() {
        return countTestRuns;
    }

    public Long getCountDeviceSessions() {
        return countDeviceSessions;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceTimeCountSessionReportEntry apiDeviceTimeCountSessionReportEntry =
                (APIDeviceTimeCountSessionReportEntry) from;
        super.clone(from);
        this.countTestRuns = apiDeviceTimeCountSessionReportEntry.countTestRuns;
        this.countDeviceSessions = apiDeviceTimeCountSessionReportEntry.countDeviceSessions;
    }
}

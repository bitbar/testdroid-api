package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIDeviceRunDataAvailability extends APIEntity {

    private Long deviceRunId;

    private boolean logs;

    private boolean performance;

    private boolean screenshots;

    private boolean buildLogs;

    private boolean resultsDataZip;

    public APIDeviceRunDataAvailability() {
    }

    public APIDeviceRunDataAvailability(Long deviceRunId, boolean logs, boolean performance, boolean screenshots,
            boolean buildLogs, boolean resultsDataZip) {
        this.deviceRunId = deviceRunId;
        this.logs = logs;
        this.performance = performance;
        this.screenshots = screenshots;
        this.buildLogs = buildLogs;
        this.resultsDataZip = resultsDataZip;
    }

    public boolean isLogs() {
        return logs;
    }

    public void setLogs(boolean logs) {
        this.logs = logs;
    }

    public boolean isPerformance() {
        return performance;
    }

    public void setPerformance(boolean performance) {
        this.performance = performance;
    }

    public boolean isScreenshots() {
        return screenshots;
    }

    public void setScreenshots(boolean screenshots) {
        this.screenshots = screenshots;
    }

    public Long getDeviceRunId() {
        return deviceRunId;
    }

    public void setDeviceRunId(Long deviceRunId) {
        this.deviceRunId = deviceRunId;
    }

    public boolean isBuildLogs() {
        return buildLogs;
    }

    public void setBuildLogs(boolean buildLogs) {
        this.buildLogs = buildLogs;
    }

    public boolean isResultsDataZip() {
        return resultsDataZip;
    }

    public void setResultsDataZip(boolean resultsDataZip) {
        this.resultsDataZip = resultsDataZip;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceRunDataAvailability apiDeviceRunDataAvailability = (APIDeviceRunDataAvailability) from;
        cloneBase(from);
        this.deviceRunId = apiDeviceRunDataAvailability.deviceRunId;
        this.logs = apiDeviceRunDataAvailability.logs;
        this.performance = apiDeviceRunDataAvailability.performance;
        this.screenshots = apiDeviceRunDataAvailability.screenshots;
        this.buildLogs = apiDeviceRunDataAvailability.buildLogs;
        this.resultsDataZip = apiDeviceRunDataAvailability.resultsDataZip;
    }
}

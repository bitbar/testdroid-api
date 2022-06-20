package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class APIUserStatistics extends APIEntity {

    private Long devicesUsed;

    private Float overallTestCaseSuccessRatio;

    private Long runningTestRuns;

    private Long totalTestRuns;

    public APIUserStatistics() {
    }

    public APIUserStatistics(
            Long devicesUsed, Float overallTestCaseSuccessRatio, Long runningTestRuns, Long totalTestRuns) {
        this.devicesUsed = devicesUsed;
        this.overallTestCaseSuccessRatio = overallTestCaseSuccessRatio;
        this.runningTestRuns = runningTestRuns;
        this.totalTestRuns = totalTestRuns;
    }

    public Long getDevicesUsed() {
        return devicesUsed;
    }

    public void setDevicesUsed(Long devicesUsed) {
        this.devicesUsed = devicesUsed;
    }

    public Float getOverallTestCaseSuccessRatio() {
        return overallTestCaseSuccessRatio;
    }

    public void setOverallTestCaseSuccessRatio(Float overallTestCaseSuccessRatio) {
        this.overallTestCaseSuccessRatio = overallTestCaseSuccessRatio;
    }

    public Long getRunningTestRuns() {
        return runningTestRuns;
    }

    public void setRunningTestRuns(Long runningTestRuns) {
        this.runningTestRuns = runningTestRuns;
    }

    public Long getTotalTestRuns() {
        return totalTestRuns;
    }

    public void setTotalTestRuns(Long totalTestRuns) {
        this.totalTestRuns = totalTestRuns;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIUserStatistics apiUserStatistics = (APIUserStatistics) from;
        cloneBase(from);
        this.devicesUsed = apiUserStatistics.devicesUsed;
        this.overallTestCaseSuccessRatio = apiUserStatistics.overallTestCaseSuccessRatio;
        this.runningTestRuns = apiUserStatistics.runningTestRuns;
        this.totalTestRuns = apiUserStatistics.totalTestRuns;
    }
}

package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIAdminDeviceSessionStatistics extends APIEntity {

    private Double avgDeviceSessionDuration;

    private Long minDeviceSessionDuration;

    private Long maxDeviceSessionDuration;

    private Double avgDeviceSessionSuccessRatio;

    private Double avgDeviceSessionDeviceTime;

    private Long totalDeviceCount;

    private Long warningDeviceCount;

    private Long runningDeviceCount;

    private Long succeededDeviceCount;

    private Long waitingDeviceCount;

    private Long abortedDeviceCount;

    private Long timeoutedDeviceCount;

    private Long errorsDeviceCount;

    private Long excludedDeviceCount;

    private Long finishedDeviceCount;

    public APIAdminDeviceSessionStatistics() {
    }

    public APIAdminDeviceSessionStatistics(
            Double avgDeviceSessionDuration, Long minDeviceSessionDuration, Long maxDeviceSessionDuration,
            Double avgDeviceSessionSuccessRatio, Double avgDeviceSessionDeviceTime) {
        this.avgDeviceSessionDuration = avgDeviceSessionDuration;
        this.minDeviceSessionDuration = minDeviceSessionDuration;
        this.maxDeviceSessionDuration = maxDeviceSessionDuration;
        this.avgDeviceSessionSuccessRatio = avgDeviceSessionSuccessRatio;
        this.avgDeviceSessionDeviceTime = avgDeviceSessionDeviceTime;
    }

    public Double getAvgDeviceSessionDuration() {
        return avgDeviceSessionDuration;
    }

    public void setAvgDeviceSessionDuration(Double avgDeviceSessionDuration) {
        this.avgDeviceSessionDuration = avgDeviceSessionDuration;
    }

    public Long getMinDeviceSessionDuration() {
        return minDeviceSessionDuration;
    }

    public void setMinDeviceSessionDuration(Long minDeviceSessionDuration) {
        this.minDeviceSessionDuration = minDeviceSessionDuration;
    }

    public Long getMaxDeviceSessionDuration() {
        return maxDeviceSessionDuration;
    }

    public void setMaxDeviceSessionDuration(Long maxDeviceSessionDuration) {
        this.maxDeviceSessionDuration = maxDeviceSessionDuration;
    }

    public Double getAvgDeviceSessionSuccessRatio() {
        return avgDeviceSessionSuccessRatio;
    }

    public void setAvgDeviceSessionSuccessRatio(Double avgDeviceSessionSuccessRatio) {
        this.avgDeviceSessionSuccessRatio = avgDeviceSessionSuccessRatio;
    }

    public Double getAvgDeviceSessionDeviceTime() {
        return avgDeviceSessionDeviceTime;
    }

    public void setAvgDeviceSessionDeviceTime(Double avgDeviceSessionDeviceTime) {
        this.avgDeviceSessionDeviceTime = avgDeviceSessionDeviceTime;
    }

    public Long getTotalDeviceCount() {
        return totalDeviceCount;
    }

    public void setTotalDeviceCount(Long totalDeviceCount) {
        this.totalDeviceCount = totalDeviceCount;
    }

    public Long getWarningDeviceCount() {
        return warningDeviceCount;
    }

    public void setWarningDeviceCount(Long warningDeviceCount) {
        this.warningDeviceCount = warningDeviceCount;
    }

    public Long getRunningDeviceCount() {
        return runningDeviceCount;
    }

    public void setRunningDeviceCount(Long runningDeviceCount) {
        this.runningDeviceCount = runningDeviceCount;
    }

    public Long getSucceededDeviceCount() {
        return succeededDeviceCount;
    }

    public void setSucceededDeviceCount(Long succeededDeviceCount) {
        this.succeededDeviceCount = succeededDeviceCount;
    }

    public Long getWaitingDeviceCount() {
        return waitingDeviceCount;
    }

    public void setWaitingDeviceCount(Long waitingDeviceCount) {
        this.waitingDeviceCount = waitingDeviceCount;
    }

    public Long getAbortedDeviceCount() {
        return abortedDeviceCount;
    }

    public void setAbortedDeviceCount(Long abortedDeviceCount) {
        this.abortedDeviceCount = abortedDeviceCount;
    }

    public Long getTimeoutedDeviceCount() {
        return timeoutedDeviceCount;
    }

    public void setTimeoutedDeviceCount(Long timeoutedDeviceCount) {
        this.timeoutedDeviceCount = timeoutedDeviceCount;
    }

    public Long getErrorsDeviceCount() {
        return errorsDeviceCount;
    }

    public void setErrorsDeviceCount(Long errorsDeviceCount) {
        this.errorsDeviceCount = errorsDeviceCount;
    }

    public Long getExcludedDeviceCount() {
        return excludedDeviceCount;
    }

    public void setExcludedDeviceCount(Long excludedDeviceCount) {
        this.excludedDeviceCount = excludedDeviceCount;
    }

    public Long getFinishedDeviceCount() {
        return finishedDeviceCount;
    }

    public void setFinishedDeviceCount(Long finishedDeviceCount) {
        this.finishedDeviceCount = finishedDeviceCount;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAdminDeviceSessionStatistics apiAdminDeviceSessionStatistics = (APIAdminDeviceSessionStatistics) from;
        cloneBase(from);
        this.avgDeviceSessionDuration = apiAdminDeviceSessionStatistics.avgDeviceSessionDuration;
        this.minDeviceSessionDuration = apiAdminDeviceSessionStatistics.minDeviceSessionDuration;
        this.maxDeviceSessionDuration = apiAdminDeviceSessionStatistics.maxDeviceSessionDuration;
        this.avgDeviceSessionSuccessRatio = apiAdminDeviceSessionStatistics.avgDeviceSessionSuccessRatio;
        this.avgDeviceSessionDeviceTime = apiAdminDeviceSessionStatistics.avgDeviceSessionDeviceTime;
        this.totalDeviceCount = apiAdminDeviceSessionStatistics.totalDeviceCount;
        this.warningDeviceCount = apiAdminDeviceSessionStatistics.warningDeviceCount;
        this.runningDeviceCount = apiAdminDeviceSessionStatistics.runningDeviceCount;
        this.succeededDeviceCount = apiAdminDeviceSessionStatistics.succeededDeviceCount;
        this.waitingDeviceCount = apiAdminDeviceSessionStatistics.waitingDeviceCount;
        this.abortedDeviceCount = apiAdminDeviceSessionStatistics.abortedDeviceCount;
        this.timeoutedDeviceCount = apiAdminDeviceSessionStatistics.timeoutedDeviceCount;
        this.errorsDeviceCount = apiAdminDeviceSessionStatistics.errorsDeviceCount;
        this.excludedDeviceCount = apiAdminDeviceSessionStatistics.excludedDeviceCount;
        this.finishedDeviceCount = apiAdminDeviceSessionStatistics.finishedDeviceCount;
    }
}

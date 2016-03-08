package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APITestRunExtended extends APITestRun {

    private Integer deviceCount;

    private APITag[] tags;

    private APIFiles files;

    private boolean billable;

    public APITestRunExtended() {
    }

    public APITestRunExtended(
            Long id, Integer number, Date createTime, String displayName, Float executionRatio, Float successRatio,
            Long startedById, String startedByDisplayName, State state, Long projectId, APIFiles files,
            Long screenshotsFileId, Long logsFileId, Integer testCaseCount, Integer successfulTestCaseCount,
            Integer totalDeviceCount, Integer finishedDeviceCount, Integer excludedDeviceCount,
            Integer errorsDeviceCount, Integer succeededDeviceCount, Integer runningDeviceCount,
            Integer warningDeviceCount, Integer waitingDeviceCount, APITag... tags) {
        super(id, number, createTime, displayName, executionRatio, successRatio, startedById, startedByDisplayName,
                state, projectId, screenshotsFileId, logsFileId, testCaseCount, successfulTestCaseCount,
                totalDeviceCount, finishedDeviceCount, excludedDeviceCount, errorsDeviceCount, succeededDeviceCount,
                runningDeviceCount, warningDeviceCount, waitingDeviceCount);
        this.deviceCount = totalDeviceCount;
        this.tags = tags;
        this.files = files;
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public APITag[] getTags() {
        return tags;
    }

    public void setTags(APITag[] tags) {
        this.tags = tags;
    }

    public APIFiles getFiles() {
        return files;
    }

    public void setFiles(APIFiles files) {
        this.files = files;
    }

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITestRunExtended apiTestRunExtended = (APITestRunExtended) from;
        super.clone(from);
        this.files = apiTestRunExtended.files;
        this.deviceCount = apiTestRunExtended.deviceCount;
        this.tags = apiTestRunExtended.tags;
        this.billable =apiTestRunExtended.billable;
    }
}

package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testdroid.api.APIEntity;
import com.testdroid.api.APIList;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APITestRunExtended extends APITestRun {

    private Integer deviceCount;

    private APITag[] tags;

    private APIList<APIUserFile> files;

    private boolean billable;

    public APITestRunExtended() {
    }

    public APITestRunExtended(
            Long id, Integer number, Date createTime, String displayName, Float executionRatio, Float successRatio,
            Long startedById, String startedByDisplayName, State state, Long userId, Long projectId,
            Long screenshotsFileId, Long logsFileId,
            Integer testCaseCount, Integer successfulTestCaseCount, Integer failedTestCaseCount,
            Integer totalDeviceCount, Integer finishedDeviceCount, Integer excludedDeviceCount,
            Integer errorsDeviceCount, Integer succeededDeviceCount, Integer runningDeviceCount,
            Integer warningDeviceCount, Integer waitingDeviceCount, Integer abortedDeviceCount,
            Integer timeoutedDeviceCount, Long frameworkId, String frameworkName, String testRunConfigurationContent) {
        super(id, number, createTime, displayName, executionRatio, successRatio, startedById, startedByDisplayName,
                state, userId, projectId, screenshotsFileId, logsFileId,
                testCaseCount, successfulTestCaseCount, failedTestCaseCount,
                totalDeviceCount, finishedDeviceCount, excludedDeviceCount, errorsDeviceCount, succeededDeviceCount,
                runningDeviceCount, warningDeviceCount, waitingDeviceCount, abortedDeviceCount, timeoutedDeviceCount,
                frameworkId, frameworkName, null);
        this.deviceCount = totalDeviceCount;
        mapConfig(testRunConfigurationContent);
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

    public APIList<APIUserFile> getFiles() {
        return files;
    }

    public void setFiles(APIList<APIUserFile> files) {
        this.files = files;
    }

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    @Override
    @JsonIgnore(false)
    public APITestRunConfig getConfig() {
        return super.getConfigOffline();
    }

    @JsonIgnore
    private void mapConfig(String content) {
        if (StringUtils.isBlank(content)) {
            return;
        }
        try {
            APITestRunConfig config = new ObjectMapper().configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(content, APITestRunConfig.class);
            setConfig(config);
        } catch (IOException ignore) {
        }
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

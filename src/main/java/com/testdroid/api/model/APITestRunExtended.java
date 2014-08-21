package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

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

    public APITestRunExtended() {
    }

    public APITestRunExtended(
            Long id, Integer number, Date createTime, String displayName, Float executionRatio, Float successRatio,
            String startedByDisplayName, State state, Long projectId, Integer deviceCount, APIFiles files,
            Long screenshotsFileId, Long logsFileId, APITag... tags) {
        super(id, number, createTime, displayName, executionRatio, successRatio, startedByDisplayName, state,
                projectId, screenshotsFileId, logsFileId);
        this.deviceCount = deviceCount;
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

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APITestRunExtended apiTestRunExtended = (APITestRunExtended) from;
        super.clone(from);
        this.files = apiTestRunExtended.files;
        this.deviceCount = apiTestRunExtended.deviceCount;
        this.tags = apiTestRunExtended.tags;
    }
}

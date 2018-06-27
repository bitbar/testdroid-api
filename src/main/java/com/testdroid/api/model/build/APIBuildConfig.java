package com.testdroid.api.model.build;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIBuildConfig extends APIEntity implements Serializable {

    private Long fileId;

    private Long executorId;

    private String configuration;

    private List<APIBuildResultConfig> resultsConfig;

    public APIBuildConfig() {
    }

    public APIBuildConfig(
            Long fileId, Long executorId, String configuration, List<APIBuildResultConfig> resultsConfig) {
        this.fileId = fileId;
        this.executorId = executorId;
        this.configuration = configuration;
        this.resultsConfig = resultsConfig;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public List<APIBuildResultConfig> getResultsConfig() {
        return resultsConfig;
    }

    public void setResultsConfig(List<APIBuildResultConfig> resultsConfig) {
        this.resultsConfig = resultsConfig;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIBuildConfig original = (APIBuildConfig) from;
        cloneBase(from);
        this.fileId = original.fileId;
        this.executorId = original.executorId;
        this.configuration = original.configuration;
        this.resultsConfig = original.resultsConfig;
    }
}

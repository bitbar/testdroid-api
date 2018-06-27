package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import com.testdroid.api.model.build.APIPipelineJob;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIBuildExecutor extends APIEntity {

    private Date createTime;

    private String displayName;

    private String name;

    private String configuration;

    private boolean enabled;

    private APIPipelineJob.Type type;

    private List<APIDeviceProperty> labels;

    public APIBuildExecutor() {
    }

    public APIBuildExecutor(
            Long id, LocalDateTime createTime, String displayName, String name, boolean enabled, String configuration,
            APIPipelineJob.Type type) {
        super(id);
        this.createTime = TimeConverter.toDate(createTime);
        this.displayName = displayName;
        this.name = name;
        this.enabled = enabled;
        this.configuration = configuration;
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<APIDeviceProperty> getLabels() {
        return labels;
    }

    public void setLabels(List<APIDeviceProperty> labels) {
        this.labels = labels;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public APIPipelineJob.Type getType() {
        return type;
    }

    public void setType(APIPipelineJob.Type type) {
        this.type = type;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIBuildExecutor original = (APIBuildExecutor) from;
        cloneBase(from);
        this.createTime = original.createTime;
        this.displayName = original.displayName;
        this.name = original.name;
        this.labels = original.labels;
        this.enabled = original.enabled;
        this.configuration = original.configuration;
        this.type = original.type;
    }
}

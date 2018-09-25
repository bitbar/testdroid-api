package com.testdroid.api.model.build;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class APIPipelineBuild extends APIEntity {

    private Long buildNumber;

    private Date createTime;

    private Long duration;

    private APIPipelineBuildStatus status;

    private APIPipelineBuildState state;

    private Long pipelineJobId;

    private Long userId;

    public APIPipelineBuild() {
    }

    public APIPipelineBuild(
            Long id, Long buildNumber, LocalDateTime createTime, Long duration, APIPipelineBuildStatus status,
            APIPipelineBuildState state, Long pipelineJobId, Long userId) {
        super(id);
        this.buildNumber = buildNumber;
        this.pipelineJobId = pipelineJobId;
        this.createTime = createTime == null ? null : Date.from(createTime.atZone(ZoneId.systemDefault()).toInstant());
        this.duration = duration;
        this.status = status;
        this.state = state;
        this.userId = userId;
    }

    public Long getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(Long buildNumber) {
        this.buildNumber = buildNumber;
    }

    public Long getPipelineJobId() {
        return pipelineJobId;
    }

    public void setPipelineJobId(Long pipelineJobId) {
        this.pipelineJobId = pipelineJobId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public APIPipelineBuildStatus getStatus() {
        return status;
    }

    public void setStatus(APIPipelineBuildStatus status) {
        this.status = status;
    }

    public APIPipelineBuildState getState() {
        return state;
    }

    public void setState(APIPipelineBuildState state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIPipelineBuild apiBuild = (APIPipelineBuild) from;
        cloneBase(from);
        this.buildNumber = apiBuild.buildNumber;
        this.pipelineJobId = apiBuild.pipelineJobId;
        this.createTime = apiBuild.createTime;
        this.duration = apiBuild.duration;
        this.status = apiBuild.status;
        this.state = apiBuild.state;
        this.userId = apiBuild.userId;
    }
}

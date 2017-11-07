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
public class APIPipelineJob extends APIEntity {

    private String name;

    private Date createTime;

    private String pipeline;

    private Long userId;

    public APIPipelineJob() {

    }

    public APIPipelineJob(Long id, String name, LocalDateTime createTime, String pipeline, Long userId) {
        super(id);
        this.name = name;
        this.userId = userId;
        this.createTime = createTime == null ? null : Date.from(createTime.atZone(ZoneId.systemDefault()).toInstant());
        this.pipeline = pipeline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIPipelineJob apiBuild = (APIPipelineJob) from;
        cloneBase(from);
        this.name = apiBuild.name;
        this.userId = apiBuild.userId;
        this.createTime = apiBuild.createTime;
        this.pipeline = apiBuild.pipeline;
    }
}

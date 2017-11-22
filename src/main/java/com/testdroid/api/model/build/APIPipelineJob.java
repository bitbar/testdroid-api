package com.testdroid.api.model.build;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class APIPipelineJob extends APIEntity {

    private String name;

    private Date createTime;

    private Date archiveTime;

    private String pipeline;

    private Long userId;

    public APIPipelineJob() {

    }

    public APIPipelineJob(
            Long id, String name, LocalDateTime createTime, LocalDateTime archiveTime, String pipeline, Long userId) {
        super(id);
        this.name = name;
        this.userId = userId;
        this.createTime = of(createTime);
        this.archiveTime = of(archiveTime);
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

    public Date getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Date archiveTime) {
        this.archiveTime = archiveTime;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIPipelineJob apiBuild = (APIPipelineJob) from;
        cloneBase(from);
        this.name = apiBuild.name;
        this.userId = apiBuild.userId;
        this.createTime = apiBuild.createTime;
        this.archiveTime = apiBuild.archiveTime;
        this.pipeline = apiBuild.pipeline;
    }
}

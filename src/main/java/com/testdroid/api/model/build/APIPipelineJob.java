package com.testdroid.api.model.build;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class APIPipelineJob extends APIEntity {

    @XmlType(namespace = "APIActivity")
    public enum Type {
        BUILD,
        BROWSER_TESTING
    }

    private String name;

    private Date createTime;

    private Date archiveTime;

    private String content;

    private Long userId;

    private Type type = Type.BUILD;

    public APIPipelineJob() {

    }

    public APIPipelineJob(
            Long id, String name, LocalDateTime createTime, LocalDateTime archiveTime, String content, Type type,
            Long userId) {
        super(id);
        this.name = name;
        this.userId = userId;
        this.createTime = of(createTime);
        this.archiveTime = of(archiveTime);
        this.type = type;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Date archiveTime) {
        this.archiveTime = archiveTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIPipelineJob apiBuild = (APIPipelineJob) from;
        cloneBase(from);
        this.name = apiBuild.name;
        this.userId = apiBuild.userId;
        this.createTime = apiBuild.createTime;
        this.archiveTime = apiBuild.archiveTime;
        this.content = apiBuild.content;
        this.type = apiBuild.type;
    }
}

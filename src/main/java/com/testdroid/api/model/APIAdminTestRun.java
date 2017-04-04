package com.testdroid.api.model;

import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIAdminTestRun extends APIEntity {

    private Date createTime;

    private Date endTime;

    private Boolean appCrawlerRun;

    private APITestRun.State state;

    private Float successRatio;

    private Integer priority;

    private String projectName;

    private Date startTime;

    private String testRunName;

    protected Long startedById;

    private String userName;

    private Long projectId;

    private Long duration;

    private APITestRunConfig.Scheduler scheduler;

    private Long frameworkId;

    private String frameworkName;

    public APIAdminTestRun() {
    }

    public APIAdminTestRun(
            Long id, Date createTime, Date startTime, Date endTime, APITestRun.State state, Long startedById,
            String userName, String projectName, String testRunName, Boolean appCrawlerRun, Float successRatio,
            Integer priority, Long projectId, Long duration, APITestRunConfig.Scheduler scheduler, Long frameworkId,
            String frameworkName) {
        super(id);
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
        this.startedById = startedById;
        this.userName = userName;
        this.projectName = projectName;
        this.testRunName = testRunName;
        this.appCrawlerRun = appCrawlerRun;
        this.successRatio = successRatio;
        this.priority = priority;
        this.projectId = projectId;
        this.duration = duration;
        this.scheduler = scheduler;
        this.frameworkId = frameworkId;
        this.frameworkName = frameworkName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getTestRunName() {
        return testRunName;
    }

    public void setTestRunName(String testRunName) {
        this.testRunName = testRunName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getAppCrawlerRun() {
        return appCrawlerRun;
    }

    public void setAppCrawlerRun(Boolean appCrawlerRun) {
        this.appCrawlerRun = appCrawlerRun;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Float getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(Float successRatio) {
        this.successRatio = successRatio;
    }

    public APITestRun.State getState() {
        return state;
    }

    public void setState(APITestRun.State state) {
        this.state = state;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getStartedById() {
        return startedById;
    }

    public void setStartedById(Long startedById) {
        this.startedById = startedById;
    }

    public APITestRunConfig.Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(APITestRunConfig.Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Long getFrameworkId() {
        return frameworkId;
    }

    public void setFrameworkId(Long frameworkId) {
        this.frameworkId = frameworkId;
    }

    public String getFrameworkName() {
        return frameworkName;
    }

    public void setFrameworkName(String frameworkName) {
        this.frameworkName = frameworkName;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIAdminTestRun apiAdminTestRun = (APIAdminTestRun) from;
        cloneBase(from);
        this.createTime = apiAdminTestRun.createTime;
        this.startTime = apiAdminTestRun.startTime;
        this.endTime = apiAdminTestRun.endTime;
        this.startedById = apiAdminTestRun.startedById;
        this.userName = apiAdminTestRun.userName;
        this.state = apiAdminTestRun.state;
        this.projectName = apiAdminTestRun.projectName;
        this.testRunName = apiAdminTestRun.testRunName;
        this.appCrawlerRun = apiAdminTestRun.appCrawlerRun;
        this.successRatio = apiAdminTestRun.successRatio;
        this.priority = apiAdminTestRun.priority;
        this.projectId = apiAdminTestRun.projectId;
        this.duration = apiAdminTestRun.duration;
        this.scheduler = apiAdminTestRun.scheduler;
        this.frameworkId = apiAdminTestRun.frameworkId;
        this.frameworkName = apiAdminTestRun.frameworkName;
    }
}

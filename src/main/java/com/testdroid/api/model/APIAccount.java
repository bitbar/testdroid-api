package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
@XmlRootElement
public class APIAccount extends APIEntity {

    private Date createTime;

    private String mainUserName;

    private String mainUserEmail;

    private Integer maxTimeout;

    private Integer testScreenshotLimit;

    private Integer executorsLimit;

    public APIAccount() {

    }

    public APIAccount(Long id, Date createTime, String mainUserName, String mainUserEmail, Integer maxTimeout,
            Integer testScreenshotLimit, Integer executorsLimit) {
        super(id);
        this.createTime = createTime;
        this.mainUserName = mainUserName;
        this.mainUserEmail = mainUserEmail;
        this.maxTimeout = maxTimeout;
        this.testScreenshotLimit = testScreenshotLimit;
        this.executorsLimit = executorsLimit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMainUserName() {
        return mainUserName;
    }

    public void setMainUserName(String mainUserName) {
        this.mainUserName = mainUserName;
    }

    public String getMainUserEmail() {
        return mainUserEmail;
    }

    public void setMainUserEmail(String mainUserEmail) {
        this.mainUserEmail = mainUserEmail;
    }

    public Integer getMaxTimeout() {
        return maxTimeout;
    }

    public void setMaxTimeout(Integer maxTimeout) {
        this.maxTimeout = maxTimeout;
    }

    public Integer getTestScreenshotLimit() {
        return testScreenshotLimit;
    }

    public void setTestScreenshotLimit(Integer testScreenshotLimit) {
        this.testScreenshotLimit = testScreenshotLimit;
    }

    public Integer getExecutorsLimit() {
        return executorsLimit;
    }

    public void setExecutorsLimit(Integer executorsLimit) {
        this.executorsLimit = executorsLimit;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAccount account = (APIAccount) from;
        cloneBase(from);
        this.createTime = account.createTime;
        this.mainUserName = account.mainUserName;
        this.mainUserEmail = account.mainUserEmail;
        this.maxTimeout = account.maxTimeout;
        this.testScreenshotLimit = account.testScreenshotLimit;
        this.executorsLimit = account.executorsLimit;
    }
}

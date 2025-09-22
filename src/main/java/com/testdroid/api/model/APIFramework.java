package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIFramework extends APIEntity {

    @XmlType(namespace = "APIFramework")
    public enum Type {
        CLIENT_SIDE,
        MANUAL,
    }

    private Long accountId;

    private String accountName;

    private Boolean canRunFromUI;

    private Date createTime = new Date();

    private String description;

    private Boolean forProjects;

    private String name;

    private APIDevice.OsType osType;

    private String requiredAppExtensions;

    private String requiredTestExtensions;

    private String documentationUrl;

    private String requiredTestFileTags;

    private Boolean retryable;

    private Boolean secured;

    private Boolean skipOlderSdk;

    private Boolean skipQueue;

    private Long queueWait;

    private String type;

    private Long labelId;

    private String labelName;

    private String icon;

    private APIUserFile sampleApp;

    private APIUserFile sampleTest;

    public APIFramework() {
    }

    @SuppressWarnings("squid:S107")
    public APIFramework(
            Long id, LocalDateTime createTime, String name, String description, APIDevice.OsType osType,
            String type, Long accountId, String accountName, String requiredAppExtensions,
            String requiredTestExtensions, String requiredTestFileTags, String documentationUrl, Boolean forProjects,
            Boolean canRunFromUI, Boolean secured, Boolean retryable, Boolean skipQueue, Long queueWait,
            Boolean skipOlderSdk, Long labelId, String labelName, String icon, Long sampleAppId, Long sampleTestId) {
        super(id);
        this.createTime = TimeConverter.toDate(createTime);
        this.name = name;
        this.description = description;
        this.osType = osType;
        this.type = type;
        this.accountId = accountId;
        this.accountName = accountName;
        this.requiredAppExtensions = requiredAppExtensions;
        this.requiredTestExtensions = requiredTestExtensions;
        this.requiredTestFileTags = requiredTestFileTags;
        this.documentationUrl = documentationUrl;
        this.forProjects = forProjects;
        this.canRunFromUI = canRunFromUI;
        this.secured = secured;
        this.retryable = retryable;
        this.skipQueue = skipQueue;
        this.queueWait = queueWait;
        this.skipOlderSdk = skipOlderSdk;
        this.labelId = labelId;
        this.labelName = labelName;
        this.icon = icon;
        this.sampleApp = new APIUserFile(sampleAppId);
        this.sampleTest = new APIUserFile(sampleTestId);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public APIDevice.OsType getOsType() {
        return osType;
    }

    public void setOsType(APIDevice.OsType osType) {
        this.osType = osType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getRequiredAppExtensions() {
        return requiredAppExtensions;
    }

    public void setRequiredAppExtensions(String requiredAppExtensions) {
        this.requiredAppExtensions = requiredAppExtensions;
    }

    public String getRequiredTestExtensions() {
        return requiredTestExtensions;
    }

    public void setRequiredTestExtensions(String requiredTestExtensions) {
        this.requiredTestExtensions = requiredTestExtensions;
    }

    public String getRequiredTestFileTags() {
        return requiredTestFileTags;
    }

    public void setRequiredTestFileTags(String requiredTestFileTags) {
        this.requiredTestFileTags = requiredTestFileTags;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }

    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }

    public Boolean getForProjects() {
        return forProjects;
    }

    public void setForProjects(Boolean forProjects) {
        this.forProjects = forProjects;
    }

    public Boolean getCanRunFromUI() {
        return canRunFromUI;
    }

    public void setCanRunFromUI(Boolean canRunFromUI) {
        this.canRunFromUI = canRunFromUI;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Boolean getSecured() {
        return secured;
    }

    public void setSecured(Boolean secured) {
        this.secured = secured;
    }

    public Boolean getRetryable() {
        return retryable;
    }

    public void setRetryable(Boolean retryable) {
        this.retryable = retryable;
    }

    public Boolean getSkipOlderSdk() {
        return skipOlderSdk;
    }

    public void setSkipOlderSdk(Boolean skipOlderSdk) {
        this.skipOlderSdk = skipOlderSdk;
    }

    public Boolean getSkipQueue() {
        return skipQueue;
    }

    public void setSkipQueue(Boolean skipQueue) {
        this.skipQueue = skipQueue;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public APIUserFile getSampleApp() {
        return sampleApp;
    }

    public void setSampleApp(APIUserFile sampleApp) {
        this.sampleApp = sampleApp;
    }

    public APIUserFile getSampleTest() {
        return sampleTest;
    }

    public void setSampleTest(APIUserFile sampleTest) {
        this.sampleTest = sampleTest;
    }

    public Long getQueueWait() {
        return queueWait;
    }

    public void setQueueWait(Long queueWait) {
        this.queueWait = queueWait;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIFramework apiFramework = (APIFramework) from;
        cloneBase(from);
        this.createTime = apiFramework.createTime;
        this.name = apiFramework.name;
        this.description = apiFramework.description;
        this.osType = apiFramework.osType;
        this.type = apiFramework.type;
        this.accountId = apiFramework.accountId;
        this.accountName = apiFramework.accountName;
        this.requiredAppExtensions = apiFramework.requiredAppExtensions;
        this.requiredTestExtensions = apiFramework.requiredTestExtensions;
        this.requiredTestFileTags = apiFramework.requiredTestFileTags;
        this.documentationUrl = apiFramework.documentationUrl;
        this.forProjects = apiFramework.forProjects;
        this.canRunFromUI = apiFramework.canRunFromUI;
        this.secured = apiFramework.secured;
        this.retryable = apiFramework.retryable;
        this.skipQueue = apiFramework.skipQueue;
        this.queueWait = apiFramework.queueWait;
        this.skipOlderSdk = apiFramework.skipOlderSdk;
        this.labelId = apiFramework.labelId;
        this.labelName = apiFramework.labelName;
        this.icon = apiFramework.icon;
        this.sampleApp = apiFramework.sampleApp;
        this.sampleTest = apiFramework.sampleTest;
    }
}

package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
@XmlRootElement
public class APIFramework extends APIEntity {

    private Long accountId;

    private Boolean canRunFromUI;

    private Date createTime = new Date();

    private String description;

    private Boolean forProjects;

    private String mainUserEmail;

    private String mainUserName;

    private String name;

    private APIDevice.OsType osType;

    private String requiredAppExtensions;

    private String requiredTestExtensions;

    private String type;

    private Boolean secured;

    public APIFramework() {
    }

    public APIFramework(
            Long id, Date createTime, String name, String description, APIDevice.OsType osType,
            String type, Long accountId, String mainUserEmail, String mainUserName, String requiredAppExtensions,
            String requiredTestExtensions, Boolean forProjects, Boolean canRunFromUI, Boolean secured) {
        super(id);
        this.createTime = createTime;
        this.name = name;
        this.description = description;
        this.osType = osType;
        this.type = type;
        this.accountId = accountId;
        this.mainUserEmail = mainUserEmail;
        this.mainUserName = mainUserName;
        this.requiredAppExtensions = requiredAppExtensions;
        this.requiredTestExtensions = requiredTestExtensions;
        this.forProjects = forProjects;
        this.canRunFromUI = canRunFromUI;
        this.secured = secured;
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

    public String getMainUserEmail() {
        return mainUserEmail;
    }

    public void setMainUserEmail(String mainUserEmail) {
        this.mainUserEmail = mainUserEmail;
    }

    public String getMainUserName() {
        return mainUserName;
    }

    public void setMainUserName(String mainUserName) {
        this.mainUserName = mainUserName;
    }

    public Boolean getSecured() {
        return secured;
    }

    public void setSecured(Boolean secured) {
        this.secured = secured;
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
        this.mainUserName = apiFramework.mainUserName;
        this.mainUserEmail = apiFramework.mainUserEmail;
        this.requiredAppExtensions = apiFramework.requiredAppExtensions;
        this.requiredTestExtensions = apiFramework.requiredTestExtensions;
        this.forProjects = apiFramework.forProjects;
        this.canRunFromUI = apiFramework.canRunFromUI;
        this.secured = apiFramework.secured;
    }
}

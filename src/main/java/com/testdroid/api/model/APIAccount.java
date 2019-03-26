package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
@XmlRootElement
public class APIAccount extends APIEntity {

    private Date createTime;

    private String mainUserName;

    private String mainUserEmail;

    public APIAccount() {
    }

    public APIAccount(Long id, LocalDateTime createTime, String mainUserName, String mainUserEmail) {
        super(id);
        this.createTime = TimeConverter.toDate(createTime);
        this.mainUserName = mainUserName;
        this.mainUserEmail = mainUserEmail;
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

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIAccount account = (APIAccount) from;
        cloneBase(from);
        this.createTime = account.createTime;
        this.mainUserName = account.mainUserName;
        this.mainUserEmail = account.mainUserEmail;
    }
}

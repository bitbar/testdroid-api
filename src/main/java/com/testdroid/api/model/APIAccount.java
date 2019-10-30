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

    private String mainUserFirstName;

    private String mainUserLastName;

    private String mainUserEmail;

    public APIAccount() {
    }

    public APIAccount(
            Long id, LocalDateTime createTime, String mainUserFirstName, String mainUserLastName,
            String mainUserEmail) {
        super(id);
        this.createTime = TimeConverter.toDate(createTime);
        this.mainUserFirstName = mainUserFirstName;
        this.mainUserLastName = mainUserLastName;
        this.mainUserEmail = mainUserEmail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMainUserFirstName() {
        return mainUserFirstName;
    }

    public void setMainUserFirstName(String mainUserFirstName) {
        this.mainUserFirstName = mainUserFirstName;
    }

    public String getMainUserLastName() {
        return mainUserLastName;
    }

    public void setMainUserLastName(String mainUserLastName) {
        this.mainUserLastName = mainUserLastName;
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
        this.mainUserFirstName = account.mainUserFirstName;
        this.mainUserLastName = account.mainUserLastName;
        this.mainUserEmail = account.mainUserEmail;
    }
}

package com.testdroid.api.model;

import com.testdroid.api.APIEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
@XmlRootElement
public class APIRole extends APIEntity {

    private Date expireTime;

    private String name;

    private Integer useLimit;

    public APIRole() {
    }

    public APIRole(Long id, String name, Integer useLimit, Date expireTime) {
        super(id);
        this.name = name;
        this.useLimit = useLimit;
        this.expireTime = expireTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUseLimit() {
        return useLimit;
    }

    public void setUseLimit(Integer useLimit) {
        this.useLimit = useLimit;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isLimited() {
        return getUseLimit() != null;
    }

    public boolean isLimitUsed() {
        return getUseLimit() != null && getUseLimit() <= 0;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIRole apiRole = (APIRole) from;
        cloneBase(from);
        this.expireTime = apiRole.expireTime;
        this.name = apiRole.name;
        this.useLimit = apiRole.useLimit;
    }

}

package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.testdroid.api.APIEntity;
import com.testdroid.api.util.TimeConverter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@smartbear.com>
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIDeviceModelPool extends APIEntity {

    private String name;

    private String osVersion;

    private String location;

    private Short numberOfBrowsers;

    private Short minAvailable;

    private Short maxTotal;

    private Short running;

    private Date createTime;

    private Boolean enabled;

    private RetentionStrategy retentionStrategy;

    @XmlType(namespace = "APIDeviceModelPool", name = "APIDeviceModelPoolRetentionStrategy")
    public enum RetentionStrategy {
        CLUSTER_ON_OFF,
        MIN_FREE_MAX_TOTAL,
        POOL_MANAGER_AWARE
    }

    public APIDeviceModelPool() {

    }

    public APIDeviceModelPool(
            Long id, String name, String osVersion, String location, Boolean enabled, Short numberOfBrowsers,
            Short minAvailable, Short maxTotal, Short running, LocalDateTime createTime,
            RetentionStrategy retentionStrategy) {
        super(id);
        this.name = name;
        this.osVersion = osVersion;
        this.location = location;
        this.enabled = enabled;
        this.numberOfBrowsers = numberOfBrowsers;
        this.minAvailable = minAvailable;
        this.maxTotal = maxTotal;
        this.running = running;
        this.createTime = TimeConverter.toDate(createTime);
        this.retentionStrategy = retentionStrategy;
    }

    public String getName() {
        return name;
    }

    public Short getRunning() {
        return running;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public Short getNumberOfBrowsers() {
        return numberOfBrowsers;
    }

    public Short getMinAvailable() {
        return minAvailable;
    }

    public Short getMaxTotal() {
        return maxTotal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getLocation() {
        return location;
    }

    public void setMinAvailable(Short minAvailable) {
        this.minAvailable = minAvailable;
    }

    public void setMaxTotal(Short maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public RetentionStrategy getRetentionStrategy() {
        return retentionStrategy;
    }

    public void setRetentionStrategy(RetentionStrategy retentionStrategy) {
        this.retentionStrategy = retentionStrategy;
    }

    @Override
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceModelPool original = (APIDeviceModelPool) from;
        cloneBase(from);
        this.name = original.name;
        this.osVersion = original.osVersion;
        this.location = original.location;
        this.enabled = original.enabled;
        this.numberOfBrowsers = original.numberOfBrowsers;
        this.minAvailable = original.minAvailable;
        this.maxTotal = original.maxTotal;
        this.running = original.running;
        this.createTime = original.createTime;
        this.retentionStrategy = original.retentionStrategy;
    }
}

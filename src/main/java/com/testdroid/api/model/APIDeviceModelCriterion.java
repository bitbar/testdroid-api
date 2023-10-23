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
public class APIDeviceModelCriterion extends APIEntity {

    @XmlType(namespace = "APIDeviceModelCriterion")
    public enum Field {
        NAME,
        FINGERPRINT,
        SERIAL_ID,
        UNLOCK_GESTURE,
        SOFTWARE_VERSION,
        INIT_STEP,
        ACCOUNT,
        BROWSERS
    }

    private Date createTime;

    private Field field;

    private String labelGroupDisplayName;

    private Long labelGroupId;

    private String labelGroupName;

    public APIDeviceModelCriterion() {

    }

    public APIDeviceModelCriterion(
            Long id, LocalDateTime createTime, Field field, Long labelGroupId, String labelGroupName,
            String labelGroupDisplayName) {
        super(id);
        this.createTime = TimeConverter.toDate(createTime);
        this.field = field;
        this.labelGroupId = labelGroupId;
        this.labelGroupName = labelGroupName;
        this.labelGroupDisplayName = labelGroupDisplayName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Long getLabelGroupId() {
        return labelGroupId;
    }

    public void setLabelGroupId(Long labelGroupId) {
        this.labelGroupId = labelGroupId;
    }

    public String getLabelGroupName() {
        return labelGroupName;
    }

    public void setLabelGroupName(String labelGroupName) {
        this.labelGroupName = labelGroupName;
    }

    public String getLabelGroupDisplayName() {
        return labelGroupDisplayName;
    }

    public void setLabelGroupDisplayName(String labelGroupDisplayName) {
        this.labelGroupDisplayName = labelGroupDisplayName;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceModelCriterion deviceModelCriterion = (APIDeviceModelCriterion) from;
        cloneBase(from);
        this.createTime = deviceModelCriterion.createTime;
        this.field = deviceModelCriterion.field;
        this.labelGroupId = deviceModelCriterion.labelGroupId;
        this.labelGroupName = deviceModelCriterion.labelGroupName;
        this.labelGroupDisplayName = deviceModelCriterion.labelGroupDisplayName;
    }
}

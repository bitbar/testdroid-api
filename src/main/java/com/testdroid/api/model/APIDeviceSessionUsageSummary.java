package com.testdroid.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testdroid.api.APIEntity;

import java.util.EnumMap;
import java.util.Map;

import static com.testdroid.api.dto.MappingKey.ID;
import static com.testdroid.api.dto.MappingKey.SELF_URI;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
@JsonIgnoreProperties(value = {ID, SELF_URI})
public class APIDeviceSessionUsageSummary extends APIEntity {

    private Map<APIDeviceSessionUsage.Type, Long> usage = new EnumMap<>(APIDeviceSessionUsage.Type.class);

    private Long totalTime;

    public APIDeviceSessionUsageSummary() {

    }

    public APIDeviceSessionUsageSummary(Long automatic, Long manual, Long dedicatedAutomatic, Long dedicatedManual) {
        this.totalTime = automatic + manual + dedicatedAutomatic + dedicatedManual;
        this.usage.put(APIDeviceSessionUsage.Type.AUTOMATIC, automatic);
        this.usage.put(APIDeviceSessionUsage.Type.MANUAL, manual);
        this.usage.put(APIDeviceSessionUsage.Type.DEDICATED_AUTOMATIC, dedicatedAutomatic);
        this.usage.put(APIDeviceSessionUsage.Type.DEDICATED_MANUAL, dedicatedManual);
    }

    public Map<APIDeviceSessionUsage.Type, Long> getUsage() {
        return usage;
    }

    public void setUsage(Map<APIDeviceSessionUsage.Type, Long> usage) {
        this.usage = usage;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    @JsonIgnore
    protected <T extends APIEntity> void clone(T from) {
        APIDeviceSessionUsageSummary original = (APIDeviceSessionUsageSummary) from;
        cloneBase(from);
        this.usage = original.usage;
        this.totalTime = original.totalTime;
    }
}

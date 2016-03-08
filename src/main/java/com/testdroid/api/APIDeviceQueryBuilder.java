package com.testdroid.api;

import com.testdroid.api.model.APIDevice;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 * @author Slawomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIDeviceQueryBuilder extends APIQueryBuilder {

    private APIDevice.DeviceFilter[] deviceFilters;

    private Long[] deviceGroupIds;

    private Long[] labelIds;

    public APIDeviceQueryBuilder filterWithLabelIds(Long... labelIds) {
        this.labelIds = labelIds;
        return this;
    }

    public APIDeviceQueryBuilder filterWithDeviceGroupIds(Long... deviceGroupIds) {
        this.deviceGroupIds = deviceGroupIds;
        return this;
    }

    public APIDeviceQueryBuilder filterWithDeviceFilters(APIDevice.DeviceFilter... deviceFilters) {
        this.deviceFilters = deviceFilters;
        return this;
    }

    @Override
    public APIDeviceQueryBuilder offset(long offset) {
        return (APIDeviceQueryBuilder) super.offset(offset);
    }

    @Override
    public APIDeviceQueryBuilder limit(long limit) {
        return (APIDeviceQueryBuilder) super.limit(limit);
    }

    @Override
    public APIDeviceQueryBuilder search(String search) {
        return (APIDeviceQueryBuilder) super.search(search);
    }

    @Override
    public APIDeviceQueryBuilder sort(Class<? extends APIEntity> type, List<APISort.SortItem> sortItems) {
        return (APIDeviceQueryBuilder) super.sort(type, sortItems);
    }

    @Override
    public APIDeviceQueryBuilder filter(List<APIFilter.APIFilterItem> filterItems) {
        return (APIDeviceQueryBuilder) super.filter(filterItems);
    }

    @Override
    protected Map<String, Object> build() {
        Map<String, Object> result = super.build();
        if (ArrayUtils.isNotEmpty(labelIds)) {
            result.put("label_id[]", StringUtils.join(labelIds, ","));
        }
        if (ArrayUtils.isNotEmpty(deviceGroupIds)) {
            result.put("device_group_id[]", StringUtils.join(deviceGroupIds, ","));
        }
        if (ArrayUtils.isNotEmpty(deviceFilters)) {
            result.put("device_filter[]", StringUtils.join(deviceFilters, ","));
        }
        return result;
    }
}

package com.testdroid.api;

import java.util.List;
import java.util.Map;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class APIDeviceGroupQueryBuilder extends APIQueryBuilder {

    private Boolean withPublic = Boolean.FALSE;

    public APIDeviceGroupQueryBuilder withPublic() {
        withPublic = Boolean.TRUE;
        return this;
    }

    @Override
    public APIDeviceGroupQueryBuilder offset(long offset) {
        return (APIDeviceGroupQueryBuilder) super.offset(offset);
    }

    @Override
    public APIDeviceGroupQueryBuilder limit(long limit) {
        return (APIDeviceGroupQueryBuilder) super.limit(limit);
    }

    @Override
    public APIDeviceGroupQueryBuilder search(String search) {
        return (APIDeviceGroupQueryBuilder) super.search(search);
    }

    @Override
    public APIDeviceGroupQueryBuilder sort(Class<? extends APIEntity> type, List<APISort.SortItem> sortItems) {
        return (APIDeviceGroupQueryBuilder) super.sort(type, sortItems);
    }

    @Override
    public APIDeviceGroupQueryBuilder filter(List<APIFilter.APIFilterItem> filterItems) {
        return (APIDeviceGroupQueryBuilder) super.filter(filterItems);
    }

    @Override
    protected Map<String, Object> build() {
        Map<String, Object> result = super.build();
        if (withPublic) {
            result.put("withPublic", "true");
        }
        return result;
    }
}

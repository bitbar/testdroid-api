package com.testdroid.api.dto;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APISort;
import com.testdroid.api.filter.FilterEntry;

import java.util.List;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class Context<T extends APIEntity> {

    public static final int DEFAULT_LIMIT = 20;

    public static final int DEFAULT_OFFSET = 0;

    public static final String FILTER_REQUEST_PARAM = "filter";

    public static final String SEARCH_REQUEST_PARAM = "search";

    public static final String FILTER_DELIMITER = ";";

    public static final String GROUP_REQUEST_PARAM = "group";

    public static final String LIMIT_REQUEST_PARAM = "limit";

    public static final String SORT_REQUEST_PARAM = "sort";

    public static final String OFFSET_REQUEST_PARAM = "offset";

    private int limit;

    private int offset;

    private String search;

    private APISort sort;

    private List<String> groups;

    private List<FilterEntry> filters;

    private Class<T> type;

    private Boolean cacheable = Boolean.FALSE;

    private Context() {
    }

    public Context(
            Class<T> type, int offset, int limit, String search, String sort, List<FilterEntry> filters,
            List<String> groups) {
        this.offset = offset;
        this.limit = limit;
        this.search = search;
        this.type = type;
        this.sort = APISort.deserialize(sort);
        this.filters = filters;
        this.groups = groups;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public APISort getSort() {
        return sort;
    }

    public Class<? extends APIEntity> getType() {
        return type;
    }

    public List<FilterEntry> getFilters() {
        return filters;
    }

    public List<String> getGroups() {
        return groups;
    }

    public Boolean getCacheable() {
        return cacheable;
    }

    public void setCacheable(Boolean cacheable) {
        this.cacheable = cacheable;
    }
}

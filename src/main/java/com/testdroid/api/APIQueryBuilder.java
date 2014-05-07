package com.testdroid.api;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sławomir Pawluk <slawomir.pawluk@bitbar.com>
 */
public class APIQueryBuilder {

    /**
     * Defines APIList limit. DefaultValue: 10
     */
    private long limit;

    /**
     * Defines APIList offset. Default value: 0
     */
    private long offset;

    /**
     * Defines APIList search. Default value: null
     */
    private String search;

    /**
     * Defines APIList sort. Default value: null
     */
    private APISort sort;

    public APIQueryBuilder() {
        this.offset = 0;
        this.limit = 10;
        this.search = null;
        this.sort = null;
    }

    public APIQueryBuilder offset(long offset) {
        if (offset > 0) {
            this.offset = offset;
        } else {
            this.offset = 0;
        }
        return this;
    }

    public APIQueryBuilder limit(long limit) {
        if (limit > 0) {
            this.limit = limit;
        }
        return this;
    }

    public APIQueryBuilder search(String search) {
        this.search = search;
        return this;
    }

    public APIQueryBuilder sort(Class<? extends APIEntity> type, APISort.SortItem... sortItems) {
        this.sort = APISort.create(type, sortItems);
        return this;
    }

    protected Map<String, Object> build() {
        return new HashMap<String, Object>() {{
            put("limit", limit);
            put("offset", offset);
            put("search", search);
            put("sort", sort != null ? sort.serialize() : null);
        }};
    }
}

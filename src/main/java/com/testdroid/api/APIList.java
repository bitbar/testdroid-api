package com.testdroid.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdroid.api.dto.Context;
import com.testdroid.api.filter.FilterEntry;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

import static com.testdroid.api.dto.Context.*;
import static java.util.stream.Collectors.joining;

/**
 * @author Łukasz Kajda <lukasz.kajda@bitbar.com>
 */
@JsonIgnoreProperties(value = {"id"})
public class APIList<T extends APIEntity> extends APIEntity {

    @XmlElementWrapper
    private List<T> data;

    private Integer limit;

    private String next;

    private Integer offset;

    private String previous;

    private String search;

    private String sort;

    private Integer total;

    @JsonIgnore
    @XmlTransient
    private Context<T> context;

    public APIList() {
        data = new ArrayList<>();
        total = 0;
    }

    public APIList(String requestURL, List<T> data, Integer total, Context<T> ctx) {
        this.total = total;
        this.offset = ctx.getOffset();
        this.limit = ctx.getLimit();
        this.search = ctx.getSearch();
        this.sort = ctx.getSort().serialize();
        this.data = data;
        this.context = ctx;
        String filter = filterParam(ctx.getFilters());
        if (offset + limit < total) {
            this.next = getListURL(requestURL, offset.longValue() + limit, limit, search, sort, filter);
        }
        if (offset - limit >= 0) {
            this.previous = getListURL(requestURL, offset.longValue() - limit, limit, search, sort, filter);
        }
    }

    private String getListURL(String requestURL, long offset, long limit, String search, String sort, String filter) {
        return String.format("%s?%s=%s&%s=%s&%s=%s&%s=%s&%s=%s", requestURL, OFFSET_REQUEST_PARAM, offset,
                LIMIT_REQUEST_PARAM, limit, SEARCH_REQUEST_PARAM, search, SORT_REQUEST_PARAM, sort,
                FILTER_REQUEST_PARAM, filter);
    }

    /**
     * Get full URL of the next page of the collection.
     * Simple call it to fetch next items.
     */
    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    private boolean isNextAvailable() {
        return offset + limit < total && !data.isEmpty();
    }

    @JsonIgnore
    public APIList<T> getNextItems() throws APIException {
        if (!isNextAvailable()) {
            return null;
        }
        return new APIListResource<>(client, selfURI, context.setOffset(offset + limit)).getEntity();
    }

    private boolean isPreviousAvailable() {
        return offset > 0;
    }

    @JsonIgnore
    public APIList<T> getPreviousItems() throws APIException {
        if (!isPreviousAvailable()) {
            return null;
        }
        return new APIListResource<>(client, selfURI, context.setOffset(offset - limit)).getEntity();
    }

    /**
     * Get full URL of the previous page of the collection.
     * Simple call it to fetch previous items.
     */
    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * Get found data of the list.
     * List contains only set of items contrained with <code>offset</code>,
     * <code>limit</code> and <code>search</code>.
     */
    @XmlTransient
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public T get(int index) {
        return this.data.get(index);
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    /**
     * Get offset of data page returned in <code>getData()</code> method.
     */
    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * Get limit of data page returned in <code>getData()</code> method.
     */
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * Get total number of items to be returned - independent from paging.
     */
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * Get search phrase used during retrieving data returned in <code>getData()</code> method.
     */
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * Get serialized sort value used during retrieving data returned in <code>getData()</code> method.
     */
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setContext(Context<T> context) {
        this.context = context;
    }

    @XmlTransient
    public Context<T> getContext() {
        return context;
    }

    @Override
    public void refresh() throws APIException {
        clone(new APIListResource<>(client, selfURI, context).getEntity());
    }

    public static String filterParam(List<FilterEntry> filters) {
        return filters.stream().map(FilterEntry::toString).collect(joining(FILTER_DELIMITER));
    }

    @Override
    @JsonIgnore
    protected <S extends APIEntity> void clone(S from) {
        APIList<T> apiList = (APIList<T>) from;
        cloneBase(from);
        this.data = apiList.data;
        this.limit = apiList.limit;
        this.next = apiList.next;
        this.offset = apiList.offset;
        this.previous = apiList.previous;
        this.search = apiList.search;
        this.sort = apiList.sort;
        this.total = apiList.total;
    }

}

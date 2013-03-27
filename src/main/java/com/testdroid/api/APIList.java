package com.testdroid.api;

import com.testdroid.api.model.APICluster;
import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APITestRun;
import java.util.List;

/**
 *
 * @author kajdus
 */
public class APIList<T> extends APIEntity {
    private String next;
    private String previous;
    private List<T> data;
    private Long offset;
    private Long limit;
    private Long total;
    private String search;

    public APIList() {}
    public APIList(String next, String previous, List<T> data, Long total, String search) {
        this.next = next;
        this.previous = previous;
        this.data = data;
        this.total = total;
        this.search = search;
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
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * Get offset of data page returned in <code>getData()</code> method.
     */
    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    /**
     * Get limit of data page returned in <code>getData()</code> method.
     */
    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * Get total number of items to be returned - independent from paging.
     */
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
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

    @Override
    public boolean hasId() {
        return false;
    }
 
    /* 
     * API LISTS
     */
    
    public static class ProjectList extends APIList<APIProject> {  }
    public static class TestRunList extends APIList<APITestRun> {  }
    public static class ClusterList extends APIList<APICluster> {  }
}

package com.testdroid.api;

import com.testdroid.api.APIList.ClusterList;
import com.testdroid.api.APIList.ProjectList;
import com.testdroid.api.APIList.TestRunList;
import com.testdroid.api.APIList.UserList;
import com.testdroid.api.model.APICluster;
import com.testdroid.api.model.APIProject;
import com.testdroid.api.model.APIProjectJobConfig;
import com.testdroid.api.model.APIProjectSharing;
import com.testdroid.api.model.APITestRun;
import com.testdroid.api.model.APIUser;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author kajdus
 */
@XmlRootElement
@XmlSeeAlso({UserList.class, ProjectList.class, TestRunList.class, ClusterList.class})
public class APIList<T extends APIEntity> extends APIEntity {
    private String next;
    private String previous;
    private List<T> data;
    private Integer offset;
    private Integer limit;
    private Long total;
    private String search;
    private String sort;

    public APIList() {}
    public APIList(String next, String previous, List<T> data, Long total, String search, String sort) {
        this.next = next;
        this.previous = previous;
        this.data = data;
        this.total = total;
        this.search = search;
        this.sort = sort;
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

    /**
     * Get serialized sort value used during retrieving data returned in <code>getData()</code> method.
     */
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public boolean hasId() {
        return false;
    }
 
    /* 
     * API LISTS
     */
    
    @XmlRootElement public static class UserList extends APIList<APIUser> {  }
    @XmlRootElement public static class ProjectList extends APIList<APIProject> {  }
    @XmlRootElement public static class ProjectSharingList extends APIList<APIProjectSharing> {  }
    @XmlRootElement public static class ProjectJobConfigList extends APIList<APIProjectJobConfig> {  }
    @XmlRootElement public static class TestRunList extends APIList<APITestRun> {  }
    @XmlRootElement public static class ClusterList extends APIList<APICluster> {  }
}

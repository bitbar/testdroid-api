package com.testdroid.api;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

/**
 * @param <T> type of internal list elements
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
@XmlRootElement
public class PagingResult<T extends APIEntity> {

    public static <T extends APIEntity> PagingResult<T> empty() {
        return new PagingResult<>(Collections.emptyList(), 0L);
    }

    private List<T> result;

    private Long totalCount;

    public PagingResult() {
    }

    public PagingResult(List<T> result, Long totalCount) {
        this.result = result;
        this.totalCount = totalCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

}

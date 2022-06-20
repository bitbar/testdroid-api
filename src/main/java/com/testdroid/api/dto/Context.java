package com.testdroid.api.dto;

import com.testdroid.api.APIEntity;
import com.testdroid.api.APISort;
import com.testdroid.api.filter.FilterEntry;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private int limit = DEFAULT_LIMIT;

    private int offset = DEFAULT_OFFSET;

    private String search;

    private APISort sort;

    private List<String> groups = new ArrayList<>();

    private List<FilterEntry> filters = new ArrayList<>();

    private final Class<T> type;

    private Boolean cacheable = Boolean.FALSE;

    private MultiValuedMap<String, Object> extraParams = new HashSetValuedHashMap<>();

    private Long count;

    public Context(Class<T> type) {
        this.type = type;
    }

    public Context(Class<T> type, int offset, int limit, String search, String sort) {
        this.offset = offset;
        this.limit = limit;
        this.search = search;
        this.type = type;
        this.sort = APISort.deserialize(sort);
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

    public Context<T> setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public Context<T> setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public String getSearch() {
        return search;
    }

    public Context<T> setSearch(String search) {
        this.search = search;
        return this;
    }

    public APISort getSort() {
        return sort;
    }

    public Optional<APISort.SortItem> findSort(String field) {
        return sort.getSorts().stream().filter(s -> Objects.equals(s.getColumn(), field)).findAny();
    }

    public Context<T> setSort(APISort sort) {
        this.sort = sort;
        return this;
    }

    public Class<T> getType() {
        return type;
    }

    public List<FilterEntry> getFilters() {
        return filters;
    }

    public Optional<FilterEntry> findFilter(String field, Operand operand) {
        return filters.stream().filter(f -> keyAndOperandEqual(f, field, operand)).findAny();
    }

    public Context<T> setFilters(List<FilterEntry> filters) {
        this.filters = filters;
        return this;
    }

    public Context<T> addFilter(FilterEntry filterEntry) {
        this.filters.add(filterEntry);
        return this;
    }

    public List<String> getGroups() {
        return groups;
    }

    public Boolean getCacheable() {
        return cacheable;
    }

    public Context<T> setCacheable(Boolean cacheable) {
        this.cacheable = cacheable;
        return this;
    }

    public MultiValuedMap<String, Object> getExtraParams() {
        return extraParams;
    }

    public void setExtraParams(MultiValuedMap<String, Object> extraParams) {
        this.extraParams = extraParams;
    }

    public Context<T> setCount(Long count) {
        this.count = count;
        return this;
    }

    public Optional<Integer> computeMaxResult() {
        Optional<Integer> maxResult = Optional.empty();
        if (limit != 0 && limit != Integer.MAX_VALUE) {
            if (Objects.nonNull(count) && offset + limit > count) {
                maxResult = Optional.of(count.intValue() % limit);
            } else {
                maxResult = Optional.of(limit);
            }
        }
        return maxResult;
    }

    public MultiValuedMap<String, Object> build() {
        MultiValuedMap<String, Object> map = new HashSetValuedHashMap<>();
        map.put(LIMIT_REQUEST_PARAM, limit);
        map.put(OFFSET_REQUEST_PARAM, offset);
        map.put(SEARCH_REQUEST_PARAM, search);
        map.put(SORT_REQUEST_PARAM, sort != null ? sort.serialize() : null);
        map.put(FILTER_REQUEST_PARAM, filters.stream().map(FilterEntry::toString).collect(Collectors.joining(";")));
        map.put(GROUP_REQUEST_PARAM, groups);
        map.putAll(extraParams);
        return map;
    }

    public <R extends T> Context<R> as(Class<R> clazz) {
        return new Context<>(clazz, this.offset, this.limit, this.search, this.sort
                .serialize(), this.filters, this.groups);
    }

    private Boolean keyAndOperandEqual(FilterEntry fe, String field, Operand operand) {
        return Objects.equals(fe.getField(), field) && Objects.equals(fe.getOperand(), operand);
    }
}

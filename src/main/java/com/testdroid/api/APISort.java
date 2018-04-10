package com.testdroid.api;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class APISort {

    public enum Type {
        ASC("a"),
        DESC("d");

        private String urlValue;

        Type(String urlValue) {
            this.urlValue = urlValue;
        }

        public static Type fromURLValue(String urlValue) {
            for (Type t : Type.values()) {
                if (t.getURLValue().equals(urlValue)) {
                    return t;
                }
            }
            return null;
        }

        public String getURLValue() {
            return urlValue;
        }
    }

    private List<SortItem> sorts;

    private APISort() {
        this(Collections.emptyList());
    }

    public APISort(List<SortItem> items) {
        this.sorts = items;
    }

    public static APISort create(List<SortItem> items) {
        return new APISort(items);
    }

    public static APISort deserialize(String value) {
        if (StringUtils.isBlank(value)) {
            return new APISort();
        }
        List<SortItem> items = new ArrayList<>();
        String[] resultItems = value.split(":");
        for (String stringItem : resultItems) {
            String[] sortItemValues = stringItem.split("_");
            if (sortItemValues.length > 1) {
                String column = sortItemValues[0];
                Type sortType = Type.fromURLValue(sortItemValues[1]);
                if (column != null) {
                    items.add(new SortItem(column, sortType));
                }
            }
        }
        return new APISort(items);
    }

    public List<SortItem> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortItem> items) {
        this.sorts = items;
    }

    public boolean isEmpty() {
        return sorts == null || sorts.isEmpty();
    }

    public String serialize() {
        return sorts.stream().map(SortItem::toString).collect(Collectors.joining(":"));
    }

    public String toString(){
        return serialize();
    }

    public static class SortItem {

        private String column;

        private Type type;

        public SortItem(String column, Type sortType) {
            this.column = column;
            this.type = sortType;
        }

        public String getColumn() {
            return column;
        }

        public Type getType() {
            return type;
        }

        @Override
        public String toString() {
            return String.format("%s_%s", column, type.getURLValue());
        }
    }

}

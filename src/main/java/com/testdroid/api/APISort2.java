package com.testdroid.api;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class APISort2 extends APISort {

    public static enum Type {
        ASC("a"),
        DESC("d");

        private String urlValue;

        private Type(String urlValue) {
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

    private APISort2(Class<? extends APIEntity> type, SortItem... items) {
        if (items != null && items.length > 0) {
            this.sorts = Arrays.asList(items);
        } else {
            this.sorts = Collections.emptyList();
        }
    }

    public static APISort2 create(Class<? extends APIEntity> type, SortItem... items) {
        return new APISort2(type, items);
    }

    public static APISort2 deserialize(Class<? extends APIEntity> type, String value) {
        if (StringUtils.isBlank(value)) {
            return new APISort2(type);
        }
        List<SortItem> items = new ArrayList<SortItem>();
        String[] resultItems = value.split(":");
        for (String stringItem : resultItems) {
            String[] sortItemValues = stringItem.split("_");
            if (sortItemValues.length > 1) {
                String column = sortItemValues[0];
                Type sortType = Type.fromURLValue(sortItemValues[1]);
                if (column != null && type != null) {
                    items.add(new SortItem(column, sortType));
                }
            }
        }
        return new APISort2(type, items.toArray(new SortItem[items.size()]));
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
        List<String> resultItems = new ArrayList<String>();
        for (SortItem item : sorts) {
            resultItems.add(String.format("%s_%s", item.column, item.type.getURLValue()));
        }
        return StringUtils.join(resultItems, ":");
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

    }

}

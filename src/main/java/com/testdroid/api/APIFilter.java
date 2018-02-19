package com.testdroid.api;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Michał Szpruta <michal.szpruta@bitbar.com>
 */
public class APIFilter {

    private List<APIFilterItem> filterItems;

    public APIFilter() {
        this.filterItems = Collections.emptyList();
    }

    public APIFilter(List<APIFilterItem> filterItems) {
        this.filterItems = filterItems;
    }

    public static APIFilter create(List<APIFilterItem> filterItems) {
        return new APIFilter(filterItems);
    }

    public String serialize() {
        List<String> resultItems = filterItems.stream().map(APIFilterItem::serialize).collect(Collectors.toList());
        return StringUtils.join(resultItems, ";");
    }

    public static class APIFilterItem {

        public enum Operand {
            GT,
            AFTER,
            LT,
            BEFORE,
            ON,
            EQ,
            ISNULL,
            IN,
            NOTIN,
            INORNULL,
            BEFOREORNULL,
            BEFOREOREQUAL,
            AFTERORNULL
        }

        public enum Type {
            N,
            D,
            S,
            B,
            LN,
            LD,
            LS,
            LB
        }

        private String fieldName;

        private Operand operand;

        private Type type;

        private Object[] values;

        public APIFilterItem(Type type, String fieldName, Operand operand, Object... values) {
            this.type = type;
            this.fieldName = fieldName;
            this.operand = operand;
            this.values = values;
        }

        private String serialize() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(type.name().toLowerCase());
            stringBuilder.append("_");
            stringBuilder.append(fieldName);
            stringBuilder.append("_");
            stringBuilder.append(operand.name().toLowerCase());
            if (values.length > 0) {
                stringBuilder.append("_");
                for (Object object : values) {
                    stringBuilder.append(object);
                    stringBuilder.append("|");
                }
                stringBuilder.setLength(stringBuilder.length() - 1);
            }
            return stringBuilder.toString();
        }
    }
}

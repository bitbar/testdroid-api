package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;
import com.testdroid.api.util.TimeConverter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class FilterEntry {

    private static final String ARG_DELIMITER = "|";

    private String field;

    private Operand operand;

    private String rawValue = EMPTY;

    public FilterEntry(String field, Operand operand, Object value) {
        this.field = Objects.requireNonNull(field);
        this.operand = Objects.requireNonNull(operand);
        this.rawValue = valueToString(value);
        validate();
    }

    public FilterEntry(String field, Operand operand) {
        this.field = Objects.requireNonNull(field);
        this.operand = Objects.requireNonNull(operand);
        validate();
    }

    private FilterEntry(String field, Operand operand, String rawValue) {
        this.field = Objects.requireNonNull(field);
        this.operand = Objects.requireNonNull(operand);
        this.rawValue = rawValue;
        validate();
    }

    public String getField() {
        return field;
    }

    public FilterEntry setField(String field) {
        this.field = field;
        return this;
    }

    public Operand getOperand() {
        return operand;
    }

    public FilterEntry setOperand(Operand operand) {
        this.operand = operand;
        return this;
    }

    public String getRawValue() {
        return rawValue;
    }

    public static FilterEntry create(String field, Operand operand, String rawValue) {
        return new FilterEntry(field, operand, rawValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilterEntry that = (FilterEntry) o;
        return Objects.equals(field, that.field)
                && operand == that.operand
                && StringUtils.equalsIgnoreCase(rawValue, that.rawValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, operand, rawValue);
    }

    @Override
    public String toString() {
        String result = String.format("%s_%s", field, operand);
        return StringUtils.isNotBlank(rawValue) ? String.format("%s_%s", result, rawValue) : result;
    }

    //region Quick filters

    public static FilterEntry trueFilterEntry(String field) {
        return new FilterEntry(field, Operand.EQ, Boolean.TRUE);
    }

    public static FilterEntry falseFilterEntry(String field) {
        return new FilterEntry(field, Operand.EQ, Boolean.FALSE);
    }

    public static FilterEntry nullFilterEntry(String field) {
        return new FilterEntry(field, Operand.ISNULL);
    }

    public static FilterEntry notNullFilterEntry(String field) {
        return new FilterEntry(field, Operand.ISNOTNULL);
    }

    //endregion

    //private methods

    private String valueToString(Object value) {
        switch (operand.getArity()) {
            case 1:
                return null;
            case 2:
                return singleToString(value);
            case Integer.MAX_VALUE:
            default:
                return ((List<?>) value).stream().map(this::singleToString).collect(Collectors.joining(ARG_DELIMITER));
        }
    }

    private void validate() {
        if (StringUtils.isNotBlank(rawValue) && operand.getArity().equals(1)) {
            throw new IllegalArgumentException("Operand does not accept any value");
        }
        if (StringUtils.isBlank(rawValue) && operand.getArity().equals(Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Operand requires a list of values");
        }
    }

    private String singleToString(Object value) {
        if (LocalDateTime.class == value.getClass()) {
            return String.valueOf(TimeConverter.toMilli((LocalDateTime) value));
        } else {
            return value.toString();
        }

    }

    //endregion
}

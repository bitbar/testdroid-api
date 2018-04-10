package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class FilterEntry<T> {

    private String field;

    private Operand operand;

    private T value;

    public FilterEntry(String field, Operand comparison, T value) {
        this.field = field;
        this.operand = comparison;
        this.value = value;
    }

    public FilterEntry(String field, Operand comparison) {
        this.field = field;
        this.operand = comparison;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Operand getOperand() {
        return operand;
    }

    public void setOperand(Operand operand) {
        this.operand = operand;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FilterEntry)) {
            return false;
        }

        FilterEntry that = (FilterEntry) o;

        if (field != null ? !field.equals(that.field) : that.field != null) {
            return false;
        }
        if (operand != that.operand) {
            return false;
        }
        if (value != null ? !value.equals(that.value) : that.value != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = field != null ? field.hashCode() : 0;
        result = 31 * result + (operand != null ? operand.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        switch(operand.getArity()){
            case 1:
                return String.format("%s_%s", field, operand);
            case 2:
                return String.format("%s_%s_%s", field, operand, value);
            case Integer.MAX_VALUE:
            default:
                return String.format("%s_%s_%s", field, operand, value);
        }
    }
}

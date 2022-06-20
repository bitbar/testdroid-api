package com.testdroid.api.model;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * @author Damian Sniezek <damian.sniezek@smartbear.com>
 */
public class APIDuration implements Serializable {

    private static final long serialVersionUID = 1L;

    private ChronoUnit unit;

    private Long value;

    public APIDuration() {

    }

    public APIDuration(Long value, ChronoUnit unit) {
        this.unit = unit;
        this.value = value;
    }

    public ChronoUnit getUnit() {
        return unit;
    }

    public void setUnit(ChronoUnit unit) {
        this.unit = unit;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        APIDuration that = (APIDuration) o;
        return unit == that.unit &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit, value);
    }
}

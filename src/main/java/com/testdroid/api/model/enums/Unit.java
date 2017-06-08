package com.testdroid.api.model.enums;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Adrian Zybała <adrian.zybala@bitbar.com>
 */
@XmlType
public enum Unit {
    MONTH,
    DAY,
    YEAR,
    HOUR,
    RUN,
    PROJECT;

    @JsonIgnore
    public static Unit fromName(String name) {
        for (Unit u : values()) {
            if (u.name().equals(name)) {
                return u;
            }
        }
        return null;
    }

    @JsonIgnore
    public static Set<Unit> getTimeUnits() {
        Set<Unit> result = new HashSet<>();
        for (Unit u : values()) {
            if (u.isTimeUnit()) {
                result.add(u);
            }
        }
        return result;
    }

    @JsonIgnore
    public boolean limitsRoles() {
        switch (this) {
            case RUN:
            case PROJECT:
                return true;
            default:
                return false;
        }
    }

    @JsonIgnore
    public boolean isTimeUnit() {
        switch (this) {
            case MONTH:
            case DAY:
            case YEAR:
            case HOUR:
                return true;
            default:
                return false;
        }
    }
}

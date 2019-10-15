package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;

import java.time.LocalDateTime;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class DateTimeFilterEntry extends FilterEntry<LocalDateTime> {

    public DateTimeFilterEntry(String field, Operand comparison, LocalDateTime value) {
        super(field, comparison, value);
    }

    public DateTimeFilterEntry(String field, Operand comparison) {
        super(field, comparison);
    }

    @Override
    public String toString() {
        return String.format("d_%s", super.toString());
    }
}

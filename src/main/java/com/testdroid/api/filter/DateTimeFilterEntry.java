package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;
import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class DateTimeFilterEntry extends FilterEntry<LocalDateTime> {

    @Deprecated
    public DateTimeFilterEntry(DateFilterEntry dateFilterEntry) {
        super(dateFilterEntry.getField(), dateFilterEntry.getOperand(), dateFilterEntry.getValue() == null ? null
                : TimeConverter.fromDateFields(dateFilterEntry.getValue()));
    }

    public DateTimeFilterEntry(String field, Operand comparison, LocalDateTime value) {
        super(field, comparison, value);
    }

    public DateTimeFilterEntry(String field, Operand comparison) {
        super(field, comparison);
    }
}

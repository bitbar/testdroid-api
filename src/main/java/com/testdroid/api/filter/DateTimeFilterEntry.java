package com.testdroid.api.filter;


import com.testdroid.api.util.TimeConverter;

import java.time.LocalDateTime;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class DateTimeFilterEntry extends FilterEntry<LocalDateTime> {

    public DateTimeFilterEntry(DateFilterEntry dateFilterEntry) {
        super(dateFilterEntry.getField(), dateFilterEntry.getOperand(), dateFilterEntry.getValue() == null ? null
                : TimeConverter.fromDateFields(dateFilterEntry.getValue()));
    }
}

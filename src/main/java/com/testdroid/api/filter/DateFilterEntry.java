package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;

import java.util.Date;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class DateFilterEntry extends FilterEntry<Date> {

    public DateFilterEntry(String field, Operand comparison, Date value) {
        super(field, comparison, value);
    }

    public DateFilterEntry(String field, Operand comparison) {
        super(field, comparison);
    }

}

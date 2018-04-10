package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class StringFilterEntry extends FilterEntry<String> {

    public StringFilterEntry(String field, Operand comparison, String value) {
        super(field, comparison, value);
    }

    @Override
    public String toString() {
        return String.format("s_%s", super.toString());
    }

}

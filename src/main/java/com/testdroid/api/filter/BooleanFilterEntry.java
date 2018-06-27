package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class BooleanFilterEntry extends FilterEntry<Boolean> {

    public BooleanFilterEntry(String field, Operand comparison, Boolean value) {
        super(field, comparison, value);
    }

    @Override
    public String toString() {
        return String.format("b_%s", super.toString());
    }

}

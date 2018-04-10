package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class NumberFilterEntry extends FilterEntry<Number> {

    public NumberFilterEntry(String field, Operand comparison, Number value) {
        super(field, comparison, value);
    }

    public NumberFilterEntry(String field, Operand operand) {
        super(field, operand);
    }

    @Override
    public String toString() {
        return String.format("n_%s", super.toString());
    }
}

package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;

import java.util.List;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class ListNumberFilterEntry extends FilterEntry<List<? extends Number>> {

    public ListNumberFilterEntry(String field, Operand operand, List<? extends Number> value) {
        super(field, operand, value);
    }
}

package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;

import java.util.List;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class ListStringFilterEntry extends FilterEntry<List<String>> {

    public ListStringFilterEntry(String field, Operand operand, List<String> value) {
        super(field, operand, value);
    }

    @Override
    public String toString() {
        return String.format("ls_%s", super.toString());
    }
}

package com.testdroid.api.filter;

import com.testdroid.api.dto.Operand;

import static com.testdroid.api.dto.Operand.EQ;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class BooleanFilterEntry extends FilterEntry<Boolean> {

    public BooleanFilterEntry(String field, Operand comparison, Boolean value) {
        super(field, comparison, value);
    }

    public static BooleanFilterEntry trueFilterEntry(String field){
        return new BooleanFilterEntry(field, EQ, TRUE);
    }

    public static BooleanFilterEntry falseFilterEntry(String field){
        return new BooleanFilterEntry(field, EQ, FALSE);
    }

    @Override
    public String toString() {
        return String.format("b_%s", super.toString());
    }

}

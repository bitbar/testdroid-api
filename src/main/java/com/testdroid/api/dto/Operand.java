package com.testdroid.api.dto;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public enum Operand {
    GT(2),
    AFTER(2),
    LT(2),
    BEFORE(2),
    ON(2),
    EQ(2),
    CONTAINS(2),
    LIKE(2),
    EMPTY(1),
    ISNULL(1),
    ISNOTNULL(1),
    IN(Integer.MAX_VALUE),
    NOTIN(Integer.MAX_VALUE),
    INORNULL(Integer.MAX_VALUE),
    BEFOREORNULL(2),
    BEFOREOREQUAL(2),
    AFTERORNULL(2),
    AFTEROREQUAL(2);

    private final int arity;

    Operand(Integer arity) {
        this.arity = arity;
    }

    public Integer getArity() {
        return arity;
    }
}

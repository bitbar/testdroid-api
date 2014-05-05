package com.testdroid.api.formatter;

import java.util.Locale;

/**
 * @author damian.sniezek
 */
public class CurrencyFormatter {

    public static String format(Integer value) {
        return String.format(Locale.ENGLISH, "%.2f", ((float) value) / 100);
    }
}

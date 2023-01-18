package com.testdroid.api.formatter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 */
public class CurrencyFormatter {

    private CurrencyFormatter() {
        throw new IllegalStateException("Utility class");
    }

    public static String format(Long value) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(BigDecimal.valueOf(value, 2));
    }

}

package com.testdroid.api.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Adrian Zybala <adrian.zybala@bitbar.com>
 */
public class TimeConverter {

    private TimeConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime toLocalDateTime(Long millis) {
        return millis == null ? null : Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime fromDateFields(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return fromCalendarFields(calendar);
    }

    /**
     * Emulates behaviour of joda time's LocalDateTime fromCalendarFields function
     */
    public static LocalDateTime fromCalendarFields(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        int era = calendar.get(Calendar.ERA);
        int yearOfEra = calendar.get(Calendar.YEAR);
        return LocalDateTime.of(
                (era == GregorianCalendar.AD ? yearOfEra : 1 - yearOfEra),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND),
                calendar.get(Calendar.MILLISECOND)
        );
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static long toTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000L;
    }

    public static long toMilli(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long toMilli(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    public static Long atMidnight(LocalDateTime date) {
        return date
                .atZone(ZoneId.of("UTC"))
                .truncatedTo(ChronoUnit.DAYS)
                .toInstant().toEpochMilli();
    }

}

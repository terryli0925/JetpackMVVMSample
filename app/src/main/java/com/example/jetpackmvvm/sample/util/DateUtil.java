package com.example.jetpackmvvm.sample.util;

import java.util.Calendar;

public class DateUtil {

    public static void clearTime(final Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static int getDaysBetween(final long fromTime, final long toTime) {
        return (int) (toTime - fromTime) /  (1000 * 60 * 60 * 24);
    }

    public static int getDaysBetween(final Calendar start, final Calendar end) {
        return (int) (end.getTimeInMillis() - start.getTimeInMillis()) /  (1000 * 60 * 60 * 24);
    }
}

package com.ssynhtn.money.utils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Garment on 2016/7/5.
 */
public class TimeUtils {
    public static String simpleDate(long millis, String separator) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy" + separator + "MM" + separator + "dd");
        return formatter.print(millis);
    }

    public static String simpleTime(long millis) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        return formatter.print(millis);
    }
}

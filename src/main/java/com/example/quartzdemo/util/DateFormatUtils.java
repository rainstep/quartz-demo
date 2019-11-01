package com.example.quartzdemo.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_PATTERN_TWO = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_PATTERN_TWO = "yyyy/MM/dd";

    public static final String DEFAULT_DATE_PATTERN = DATE_TIME_PATTERN;



    public static String format(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return dateFormat.format(date);
    }

    public static String format(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date parse(String source, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

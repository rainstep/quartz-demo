package com.example.quartzdemo.util;


import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static int getTimeSeconds(long timeMillis) {
        int timeSeconds = (int) (timeMillis / 1000);
        return timeSeconds;
    }


    public static Date addMinutes(Date date, int minutes) {
        return org.apache.commons.lang3.time.DateUtils.addMinutes(date, minutes);
    }

    public static Date addDays(Date date, int days) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, days);
    }

    public static Date addDaysBegin(Date date, int days) {
        date=getDayBegin(date);
        return org.apache.commons.lang3.time.DateUtils.addDays(date, days);
    }

    public static int truncatedCompareTo(Date date1, Date date2, int field) {
        return org.apache.commons.lang3.time.DateUtils.truncatedCompareTo(date1, date2, field);
    }

    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    public static Date getDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 1);
        return calendar.getTime();
    }

    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getMonthBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 1);
        return calendar.getTime();
    }

    public static Date addMonths(Date date, int months) {
        return org.apache.commons.lang3.time.DateUtils.addMonths(date, months);
    }

    public static Date addSeconds(Date date, int seconds) {
        return org.apache.commons.lang3.time.DateUtils.addSeconds(date, seconds);
    }
}

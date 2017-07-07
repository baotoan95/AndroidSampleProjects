package com.confidence.btit95.confidencesecretbeta.utils;

import java.util.Calendar;

/**
 * Created by baotoan on 05/07/2017.
 */

public class DateUtils {
    public static String getMonthAsString(int month) {
        ++month;
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Unknown";
        }
    }

    public static String getWeekdayAsString(int weekday) {
        switch (weekday) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return "Unknown";
        }
    }

    public static int getWeekday(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String formatTime24Hours(int hour, int minute) {
        // Validate inputs
        if(hour < 0 || hour > 24 || minute < 0 || minute > 59) {
            return "Unknown";
        }
        // Format date
        String fHour = String.valueOf(hour);
        String fMinute = String.valueOf(minute);
        if(hour < 10) {
            fHour = "0".concat(fHour);
        }
        if(minute < 10) {
            fMinute = "0" + minute;
        }
        return fHour + ":" + fMinute;
    }
}

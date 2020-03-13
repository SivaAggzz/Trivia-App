package com.techneapps.triviaapp.utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtils {
    //convert the system generated timestamp to human readable
    static String getDateTimeFromMillis(long milliseconds){
        String finalParsedDate="";

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd", Locale.getDefault());
        finalParsedDate= dateFormatter.format(milliseconds);
        String firstPartOfDate = getDayOfMonthSuffix(Integer.parseInt(finalParsedDate));
        finalParsedDate+=firstPartOfDate+" ";

        SimpleDateFormat restDateFormat = new SimpleDateFormat("MMMM hh:mm a", Locale.getDefault());
        String secondPartOfDate= restDateFormat.format(milliseconds);
        finalParsedDate+=secondPartOfDate;
        return finalParsedDate;
    }

    //getDayOfMonthSuffix according to provided date
    private static String getDayOfMonthSuffix(int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }
}

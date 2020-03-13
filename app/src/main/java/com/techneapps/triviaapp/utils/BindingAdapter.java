package com.techneapps.triviaapp.utils;

import android.widget.TextView;

import static com.techneapps.triviaapp.utils.DateTimeUtils.getDateTimeFromMillis;

public class BindingAdapter {
    /*
     * collections of methods which are used at the time of data binding
     */

    //convert the system generated timestamp to human readable and set it to the concerned textview
    @androidx.databinding.BindingAdapter(value = "setDateTime")
    public static void setDateTime(TextView textView,long dateTimeInLong){
        textView.setText(" : "+getDateTimeFromMillis(dateTimeInLong));
    }
}

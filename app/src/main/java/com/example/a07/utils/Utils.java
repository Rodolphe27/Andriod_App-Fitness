package com.example.a07.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale + 0.5f);
    }

    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static String getCurrentDateAndTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:YYYY HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

}


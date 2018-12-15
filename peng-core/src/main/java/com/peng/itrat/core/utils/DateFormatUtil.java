package com.peng.itrat.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    private static final String DATETIME_SDF = "yyyy-MM-dd HH:mm:ss";

    public DateFormatUtil() {
    }

    public static Date formatDateTime(String datetimeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date e = sdf.parse(datetimeStr);
            return e;
        } catch (ParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }
}
package com.tositteach.util;

import java.util.Calendar;

public class YearIdBuilder {
    public static String build(String baseId) {
        Calendar cal = Calendar.getInstance();
        String year = cal.get(Calendar.YEAR) + "";
        if (baseId == null || year.compareTo(baseId.substring(0, 4)) > 0) {
            return year + "0000000";
        } else {
            String num = (Integer.parseInt(baseId.substring(4)) + 1) + "";
            StringBuilder builder = new StringBuilder(num);
            while (builder.length() < 7) builder.insert(0, '0');
            return baseId.substring(0, 4) + builder;
        }
    }
}

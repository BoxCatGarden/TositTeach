package com.tositteach.util;

import java.util.Calendar;

public class YearIdBuilder {
    public static String build(String baseId) {
        return build(baseId, "", "0000000");
    }

    public static String build(String baseId, String mid, String count) {
        Calendar cal = Calendar.getInstance();
        String year = cal.get(Calendar.YEAR) + "";
        if (baseId == null || year.compareTo(baseId.substring(0, 4)) > 0) {
            return year + mid + count;
        } else {
            String num = (Integer.parseInt(baseId.substring(4+mid.length())) + 1) + "";
            StringBuilder builder = new StringBuilder(num);
            int k = count.length();
            while (builder.length() < k) builder.insert(0, '0');
            return baseId.substring(0, 4) + mid + builder;
        }
    }

    public static void main(String[] args) {
        System.out.println("null:"+build(null));
        System.out.println("20180000001:"+build("20180000001"));
        System.out.println("null:01:0000000:"+build(null,"01","0000000"));
        System.out.println("2018010000001:"+build("2018010000001","01","0000000"));
    }
}

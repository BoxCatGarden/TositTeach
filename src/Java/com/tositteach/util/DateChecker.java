package com.tositteach.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChecker {
    public static boolean valiDate(String s) {
        if (s == null || s.length()==0) return false;
        try {
            //SimpleDateFormat线程不安全，不能做成静态变量
            new SimpleDateFormat("yyyy-MM-dd").parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean valiDuration(String st, String ed) {
        if (st == null || ed == null || st.length()==0 || ed.length()==0)
            return false;
        //SimpleDateFormat线程不安全，不能做成静态变量
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date stTime = format.parse(st);
            Date edTime = format.parse(ed);
            return stTime.after(format.parse("2018-1-1")) && stTime.before(edTime);
        } catch (ParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("null:"+ valiDate(null));
        System.out.println(":"+ valiDate(""));
        System.out.println("2019-16-2:"+ valiDate("2019-16-2"));
        System.out.println("2018-8-4:"+ valiDate("2018-8-4"));
        System.out.println("208-8-4:"+ valiDate("208-8-4"));
        System.out.println("2018-08-04:"+ valiDate("2018-08-04"));

        System.out.println("2018-8-4,2019-16-2:"+valiDuration("2018-8-4","2019-16-2"));
        System.out.println("2018-8-5,2019-16-2:"+valiDuration("2018-8-5","2019-16-2"));
        System.out.println("2018-8-5,2017-16-2:"+valiDuration("2018-8-5","2017-16-2"));
        System.out.println("2018-8-,2017-16-2:"+valiDuration("2018-8-","2017-16-2"));
        System.out.println(",2017-16-2:"+valiDuration("","2017-16-2"));
        System.out.println("2018-8-5,null:"+valiDuration("2018-8-5",null));

    }
}

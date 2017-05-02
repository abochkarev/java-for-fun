package com.tasks.java8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class TimeApi {


    public static void main(String []args) {
        // Local date
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        System.out.println(year);
        Month month = date.getMonth();
        System.out.println(month);
        int day = date.getDayOfMonth();
        System.out.println(day);
        DayOfWeek dow = date.getDayOfWeek();
        System.out.println(dow);
        int len = date.lengthOfMonth();
        System.out.println(len);
        boolean leap = date.isLeapYear();
        System.out.println(leap);

        LocalDate today = LocalDate.now();
        System.out.println(today);
    }
}

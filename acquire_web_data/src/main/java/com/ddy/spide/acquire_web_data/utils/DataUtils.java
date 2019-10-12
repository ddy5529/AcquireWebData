package com.ddy.spide.acquire_web_data.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {
    public static String SimplePattern = "yyyy-MM-dd";
    public static String CompletePattern = "yyyy-MM-dd HH:mm:ss";


    public static boolean judgeTimeOnTheParam(int beginHours, int beginMinutes, int endHours, int endMinutes) {
        boolean resultFlag = false;
        Date beginDate = getDayByHoursAndMinutes(beginHours, beginMinutes);
        Date endDate = getDayByHoursAndMinutes(endHours, endMinutes);
        Date thisDate = new Date();
        if (thisDate.after(beginDate) && thisDate.before(endDate)) {
            resultFlag = true;
        }
        return resultFlag;
    }

    public static Date getDayByHoursAndMinutes(int setHours, int setMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, setHours, setMinutes);
        return calendar.getTime();
    }


    public static void main(String[] args) {
        //System.out.println(DataUtils.judgeTimeOnTheParam(13, 00, 16, 00));
        //System.out.println(DataUtils.judgeTimeOnTheParam(13, 00, 15, 00));
        //System.out.println(judgeTimeOnTheParam(9,30,11,30));
        //System.out.println(getSimpleDate(getDayByHoursAndMinutes(10,00)));
        System.out.println(todayIsChinaHoliday());
    }

    public static boolean isHoliday() {
        boolean yesOrNot = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //判断日期是否是周六周日
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            yesOrNot = true;
        }
        //如果今天是国家法定节假日
        if (todayIsChinaHoliday()) {
            yesOrNot=true;
        }

        //如果是国家加班日，就不放假
        if (todayIsChinaWork()){
            yesOrNot=false;
        }

        return yesOrNot;
    }

    /**
     * 判断今天是否是国家加班日
     */
    private static boolean todayIsChinaWork() {
        int judgeDayArr[][] = {{12, 29},{2, 2},{2, 3},{4, 28},{5, 5},{9, 29},{10, 12}  };
        return todayInTheArrDay(judgeDayArr);
    }

    /**
     * 判断今天是否是国家法定节假日
     */
    private static boolean todayIsChinaHoliday() {
        int judgeDayArr[][] = {{12, 30}, {1, 1}
                , {2, 4}, {2, 5}, {2, 6}, {2, 7}, {2, 8}, {2, 9}, {2, 10}
                , {4, 5}, {5, 1}, {5, 2}, {5, 3}, {6, 7}, {9, 13}
                , {10, 1}, {10, 2}, {10, 3}, {10, 4}, {10, 5}, {10, 6}, {10, 7}};
        return todayInTheArrDay(judgeDayArr);
    }

    private static boolean todayInTheArrDay(int[][] judgeDayArr) {
        boolean yesOrNot = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (int[] ints : judgeDayArr) {
            int month = ints[0];
            int day = ints[1];
            Date tempDate = getTimeByMonthAndDay(month, day);
            if (thisDayIsToday(tempDate)) {
                yesOrNot=true;
                break;
            }
        }
        return yesOrNot;
    }

    /**如果当前时间和入参的时间年月日相等，则返回true*/
    private static boolean thisDayIsToday(Date tempDate) {
        boolean yesOrNot = false;
        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(new Date());

        Calendar calendarThisDay = Calendar.getInstance();
        calendarThisDay.setTime(tempDate);
        if (calendarToday.get(Calendar.YEAR) == calendarThisDay.get(Calendar.YEAR)
                && calendarToday.get(Calendar.MONTH) == calendarThisDay.get(Calendar.MONTH)
                && calendarToday.get(Calendar.DATE) == calendarThisDay.get(Calendar.DATE)
        ) {
            yesOrNot = true;
        }
        return yesOrNot;
    }

    private static Date getTimeByMonthAndDay(int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year, month, day);
        return calendar.getTime();
    }


    /**
     * 获取当前默认时间的全部信息
     */
    public static String getNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(DataUtils.CompletePattern);
        return formatter.format(new Date());
    }

    /**
     * 获取当前默认时间的简要信息
     */
    public static String getSimpleNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(DataUtils.SimplePattern);
        return formatter.format(new Date());
    }

    /**
     * 获取时间的格式化文字
     */
    public static String getSimpleDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DataUtils.SimplePattern);
        return formatter.format(date);
    }

    /**
     * 获取时间的默认格式化文字
     */
    public static String getCompleteDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DataUtils.CompletePattern);
        return formatter.format(date);
    }
}

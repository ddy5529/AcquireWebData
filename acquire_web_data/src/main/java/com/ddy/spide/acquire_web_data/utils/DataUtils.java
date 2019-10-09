package com.ddy.spide.acquire_web_data.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static String SimplePattern="yyyy-MM-dd";
    public static String CompletePattern="yyyy-MM-dd HH:mm:ss";


    public static String getNowDate(){
        SimpleDateFormat formatter = new SimpleDateFormat(DataUtils.CompletePattern);
        return formatter.format(new Date());
    }

    public static String getSimpleNowDate(){
        SimpleDateFormat formatter = new SimpleDateFormat(DataUtils.SimplePattern);
        return formatter.format(new Date());
    }

    public static boolean judgeTimeOnTheParam(int beginHours,int beginMinutes,int endHours,int endMinutes){
        boolean resultFlag=false;
        Date beginDate=new Date();
        beginDate.setHours(beginHours);
        beginDate.setMinutes(beginMinutes);
        Date endDate=new Date();
        endDate.setHours(endHours);
        endDate.setMinutes(endMinutes);
        Date thisDate=new Date();
        if (thisDate.after(beginDate)&&thisDate.before(endDate)){
            resultFlag=true;
        }
        return resultFlag;
    }

    public static void main(String[] args) {
        System.out.println(DataUtils.judgeTimeOnTheParam(13, 00, 16, 00));
        System.out.println(DataUtils.judgeTimeOnTheParam(13, 00, 15, 00));
        System.out.println(judgeTimeOnTheParam(9,30,11,30));
    }
}

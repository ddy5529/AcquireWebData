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
}

package com.ddy.spide.acquire_web_data.utils;

import java.util.List;

public class StringUtils {
    public static String getStrByList(List<String> list,String splidStr){
        StringBuilder sbl=new StringBuilder();
        for (String s : list) {
            sbl.append(s);
            sbl.append(splidStr);
        }
        return sbl.length()>0?sbl.toString().substring(0,sbl.length()-1):"";
    }
}

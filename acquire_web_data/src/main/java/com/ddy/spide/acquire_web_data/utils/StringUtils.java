package com.ddy.spide.acquire_web_data.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * 把list转成成字符串，然后用分隔符splidStr隔开
     * */
    public static String getStrByList(List<String> list,String splidStr){
        StringBuilder sbl=new StringBuilder();
        for (String s : list) {
            sbl.append(s);
            sbl.append(splidStr);
        }
        return sbl.length()>0?sbl.toString().substring(0,sbl.length()-1):"";
    }

    /**
     * 把分割符splidStr，转成成对应的参数。
     * 分割符必须是一位长度，或者是以反斜杠开头的两个长度
     * */
    public static String getStrByStrArray(String orginStr,String splidStr,String... arg){
        StringBuilder stringBuilder=new StringBuilder();
        String[] strings=orginStr.split(splidStr);
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
            if((i+1)<=appearNumber(orginStr,splidStr)){
                stringBuilder.append(arg.length>i?arg[i]:strings[i]);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 在getStrByStrArray的基础上默认使用分割符:{}
     * */
    public static String getSplitStr(String orginStr,String... arg){
        StringBuilder stringBuilder=new StringBuilder();
        String[] strings=orginStr.split("\\{}");
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
            if((i+1)<=appearNumber(orginStr,"\\{}")){
                stringBuilder.append(arg.length>i?arg[i]:strings[i]);
            }
        }
        return stringBuilder.toString();
    }
    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText 源字符串
     * @param findText 要查找的字符串
     * @return
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(getSplitStr("突破新高：{}，破高次数：","烽火",1+"",2+""));
        System.out.println(getSplitStr("突破新高：{}，破高次数：{}","烽火",1+"",2+""));
        System.out.println(getSplitStr("{}突破新高：{}，破高次数：{}","烽火",1+"",2+""));
        System.out.println(getStrByStrArray("{}突破新高：{}，破高次数：{}","\\"+"{}","烽火",1+"",2+""));
        System.out.println(getStrByStrArray("?突破新高：?，破高次数：?","\\?","烽火",1+"",2+""));
        System.out.println(getStrByStrArray("？突破新高：，破高次数：？","？","烽火",1+"",2+""));
    }

}

package com.ddy.spide.acquire_web_data.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 为了解决打日志麻烦的问题，建立了该类
 *
 * @author :yexingyuan
 * @date 2019年7月16日
 * @Update 2019年9月24日
 */
@Component
public class LogUtils2 {

    @Value("$logEnvironmentValue")
    private static String environmentValue;
    private final Logger logger;

    public LogUtils2() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public enum environment {
        DEV("dev", "1"), TEST("test", "2"), UAT("uat", "3"), PRO("pro", "4");
        private String code;
        private String name;

        environment(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 开发环境时的打印日志
     *
     * @param message
     */
    public void printDebugLog(String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        Map<String, String> tempMap = getTheadAttribute(stackTrace, 2);
        String methedClassName = tempMap.get("methedClassName");
        String methedName = tempMap.get("methedName");
        String methedLineNum = tempMap.get("methedLineNum");
        if (environmentValue.equals(environment.DEV.getCode())) {
            logger.debug("项目名：impl ,类名:{} ,方法名:{} ,行数：{},描述:{} ", methedClassName, methedName, methedLineNum, message);
        }
    }

    /**
     * 打印日志
     *
     * @param message
     */
    public void printLog(String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        Map<String, String> tempMap = getTheadAttribute(stackTrace, 2);
        String methedClassName = tempMap.get("methedClassName");
        String methedName = tempMap.get("methedName");
        String methedLineNum = tempMap.get("methedLineNum");
        logger.info("项目名：impl ,类名:{} ,方法名:{} ,行数：{},描述:{} ", methedClassName, methedName, methedLineNum, message);
    }

    /**
     * 打印异常等级为ERROR的日志
     *
     * @param message
     */
    public void printErrorLog(String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        Map<String, String> tempMap = getTheadAttribute(stackTrace, 2);
        String methedClassName = tempMap.get("methedClassName");
        String methedName = tempMap.get("methedName");
        String methedLineNum = tempMap.get("methedLineNum");
        logger.error("项目名：impl ,类名:{} ,方法名:{} ,行数：{},描述:{} ", methedClassName, methedName, methedLineNum, message);
    }

    /**
     * 获取线程对应的属性
     */
    private static Map<String, String> getTheadAttribute(StackTraceElement[] stackTrace, int level) {
        Map<String, String> tempMap = new HashMap<>();
        tempMap.put("methedClassName", getMethedClassName(stackTrace, level));
        tempMap.put("methedName", getLevelMethedName(stackTrace, level));
        tempMap.put("methedLineNum", getLevelMethedLineNum(stackTrace, level) + "");
        return tempMap;
    }

    /**
     * 获取当前线程对应的类名
     */
    public static String getMethedClassName(StackTraceElement[] stackTrace, int level) {
        StackTraceElement a = stackTrace[level];
        String fileName = a.getFileName();
        return fileName.substring(0, fileName.indexOf(".java"));
    }

    /**
     * 获取当前线程对应的方法
     */
    public static String getLevelMethedName(StackTraceElement[] stackTrace, int level) {
        StackTraceElement a = stackTrace[level];
        return a.getMethodName();
    }

    /**
     * 获取当前线程对应的方法所在的行数
     */
    public static int getLevelMethedLineNum(StackTraceElement[] stackTrace, int level) {
        StackTraceElement a = stackTrace[level];
        return a.getLineNumber();
    }

}


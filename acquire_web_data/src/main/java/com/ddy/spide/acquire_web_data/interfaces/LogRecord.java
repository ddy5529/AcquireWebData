package com.ddy.spide.acquire_web_data.interfaces;

public @interface LogRecord {
    String values() default "";

    String descript() default "自定义管理员注解";
}

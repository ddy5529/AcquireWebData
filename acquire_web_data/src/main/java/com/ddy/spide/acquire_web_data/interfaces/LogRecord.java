package com.ddy.spide.acquire_web_data.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
public @interface LogRecord {
    String values() default "";

    String descript() default "自定义管理员注解";
}

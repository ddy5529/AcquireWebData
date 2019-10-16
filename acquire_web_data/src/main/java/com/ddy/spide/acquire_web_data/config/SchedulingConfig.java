package com.ddy.spide.acquire_web_data.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务配置信息
 * */
@Configuration
@ComponentScan({"com.ddy.spide.acquire_web_data.schedul"})
@EnableScheduling
public class SchedulingConfig {

}

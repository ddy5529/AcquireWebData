package com.ddy.spide.acquire_web_data.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"com.ddy.spide.acquire_web_data.schedul"})
@EnableScheduling
public class SchedulingConfig {

}

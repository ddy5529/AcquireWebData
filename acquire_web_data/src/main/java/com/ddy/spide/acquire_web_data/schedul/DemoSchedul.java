package com.ddy.spide.acquire_web_data.schedul;

import com.ddy.spide.acquire_web_data.utils.DataUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class DemoSchedul {

    //初始延迟1秒，每隔2秒
    @Scheduled(fixedRateString = "2000", initialDelay = 1000)
    public void testFixedRate() {

        System.out.println("fixedRateString,当前时间：" + DataUtils.getNowDate());
    }

    //每次执行完延迟2秒
    @Scheduled(fixedDelayString = "2000")
    public void testFixedDelay() {
        System.out.println("fixedDelayString,当前时间：" + DataUtils.getNowDate());
    }

    //每隔3秒执行一次
    @Scheduled(cron = "0/3 * * * * ?")
    public void testCron() {
        System.out.println("cron,当前时间：" + DataUtils.getNowDate());
    }
}

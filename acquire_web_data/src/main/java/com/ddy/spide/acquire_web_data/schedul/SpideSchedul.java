package com.ddy.spide.acquire_web_data.schedul;

import com.ddy.spide.acquire_web_data.service.SpideSINAFinanceService;
import com.ddy.spide.acquire_web_data.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SpideSchedul {

    @Autowired
    private SpideSINAFinanceService spideSINAFinanceService;

    /**
     * TODO:这里要改成9点30-11点30，和13:00-15:00
     */
    @Scheduled(cron = "0/1 * 9-15 * * ?")
    public void testCron() {
        if (DataUtils.judgeTimeOnTheParam(9, 30, 11, 30)) {
            spideSINAFinanceService.getFHTXData();
        } else if (DataUtils.judgeTimeOnTheParam(13, 00, 15, 00)) {
            spideSINAFinanceService.getFHTXData();
        }
    }

    /**
     * 日终任务:每天下午4点进行清盘结算
     * TODO：把redis的数据放入数据库中进行持久化
     */
    @Scheduled(cron = "0 * 16 * * ?")
    public void dayEnd() {
        spideSINAFinanceService.dayEnd();
    }

}

package com.ddy.spide.acquire_web_data.schedul;

import com.ddy.spide.acquire_web_data.service.SpideSINAFinanceService;
import com.ddy.spide.acquire_web_data.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SpideSchedul {

    @Autowired
    private SpideSINAFinanceService spideSINAFinanceService;

    @Scheduled(cron = "0/1 * * * * ?")
    public void testCron() {
        spideSINAFinanceService.getFHTXData();
    }

}

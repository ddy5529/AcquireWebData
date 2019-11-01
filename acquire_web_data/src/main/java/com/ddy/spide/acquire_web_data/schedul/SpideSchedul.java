package com.ddy.spide.acquire_web_data.schedul;

import com.ddy.spide.acquire_web_data.service.SpideSINAFinanceService;
import com.ddy.spide.acquire_web_data.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class SpideSchedul {

    @Autowired
    private SpideSINAFinanceService spideSINAFinanceService;

    /**
     * TODO:这里要改成9点30-11点30，和13:00-15:00
     */
    @Scheduled(cron = "0/1 * 9-15 * * ?")
    public void testCron() {
        List<String> list=new ArrayList<>();
        list.add("sh600498");
        //周六周日不做操作
        if(DataUtils.isHoliday()){
            return;
        }
        if (DataUtils.judgeTimeOnTheParam(9, 29, 11, 30)) {
            spideSINAFinanceService.getStockByTheSequenceNum(list);
        } else if (DataUtils.judgeTimeOnTheParam(12, 59, 15, 00)) {
            spideSINAFinanceService.getStockByTheSequenceNum(list);
        }
    }

    /**
     * 日终任务:每天下午4点进行清盘结算
     * TODO：把redis的数据放入数据库中进行持久化
     */
    @Scheduled(cron = "0 0 16 * * ?")
    public void dayEnd() {
        List<String> list=new ArrayList<>();
        list.add("sh600498");
        spideSINAFinanceService.dayEnd(list);
    }

}

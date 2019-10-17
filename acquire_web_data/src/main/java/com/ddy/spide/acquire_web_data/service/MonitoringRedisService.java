package com.ddy.spide.acquire_web_data.service;

//监控Redis中相应的指标值
public interface MonitoringRedisService {
    //TODO：获取破高和破低的记录值，用于webService的数据推送

    void pushWebServiceMessage(String msg);

    //TODO：获取计算后的这次股票的增长幅度和速率

}

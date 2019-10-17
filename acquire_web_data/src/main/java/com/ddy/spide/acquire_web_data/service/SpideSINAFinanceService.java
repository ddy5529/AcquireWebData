package com.ddy.spide.acquire_web_data.service;

import java.util.List;

/**
 * 爬取新浪财经的服务
 * */
public interface SpideSINAFinanceService {

    /**
     * 新增数据推送到网页中
     * */
    String pushWebSocketData(String msg);

    void dayEnd(List<String> list);

    /**
     * 计算一只股票的波动速率,用于判断价格的变化会成为一个什么样
     */
    void calculateStockFluctuationRate();

    /**
     * 计算一只股票当天的资本变化结构，用于看看到底是散户还是庄家在玩
     */
    void calculateStockCapital();

    void getStockByTheSequenceNum(List<String> paramStockNumList);
}

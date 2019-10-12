package com.ddy.spide.acquire_web_data.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class MonitorStock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="MonitorStock")
    @SequenceGenerator(name="MonitorStock", sequenceName="seq_monitorstock")
    private int id;

    private String stockName;//股票名称

    private String stockNum;//股票编号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }
}

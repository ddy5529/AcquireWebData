package com.ddy.spide.acquire_web_data.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 股票标记
 * */
@Entity
public class StockMark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="stockmark")
    @SequenceGenerator(name="stockmark", sequenceName="seq_stockmark")
    private int id;

    private String stockName;//股票名称

    private String stockNum;//股票编号

    private Double hightRecord;//当天最高记录

    private int breakTheHightRecordCount;//破高记录

    private Double lowRecord;//当天最低记录

    private int breakTheLowRecordCount;//破底记录

    private Date createTime;

    private Date updateTime;

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

    public Double getHightRecord() {
        return hightRecord;
    }

    public void setHightRecord(Double hightRecord) {
        this.hightRecord = hightRecord;
    }

    public int getBreakTheHightRecordCount() {
        return breakTheHightRecordCount;
    }

    public void setBreakTheHightRecordCount(int breakTheHightRecordCount) {
        this.breakTheHightRecordCount = breakTheHightRecordCount;
    }

    public Double getLowRecord() {
        return lowRecord;
    }

    public void setLowRecord(Double lowRecord) {
        this.lowRecord = lowRecord;
    }

    public int getBreakTheLowRecordCount() {
        return breakTheLowRecordCount;
    }

    public void setBreakTheLowRecordCount(int breakTheLowRecordCount) {
        this.breakTheLowRecordCount = breakTheLowRecordCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "StockMark{" +
                "id=" + id +
                ", stockName='" + stockName + '\'' +
                ", stockNum='" + stockNum + '\'' +
                ", hightRecord=" + hightRecord +
                ", breakTheHightRecordCount=" + breakTheHightRecordCount +
                ", lowRecord=" + lowRecord +
                ", breakTheLowRecordCount=" + breakTheLowRecordCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

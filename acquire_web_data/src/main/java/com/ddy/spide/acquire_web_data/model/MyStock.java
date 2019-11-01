package com.ddy.spide.acquire_web_data.model;

import org.springframework.context.annotation.Lazy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 我的股票
 * */
@Entity
@Lazy
public class MyStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String stockName;//购入股票名称

    private String stockNum;//购入股票编号

    private Double stockShopPrice; //购入价格

    private int stockShopNum; //股票编号

    private Date stockShopDate; //卖出价格

    private Double stockShopTotalMoney;//买入总额

    private Double stockSalePrice;//买入价格

    private int stockSaleNum;//股票卖出数量

    private Double stockSaleTotalMoney;//卖出总额

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

    public Double getStockShopPrice() {
        return stockShopPrice;
    }

    public void setStockShopPrice(Double stockShopPrice) {
        this.stockShopPrice = stockShopPrice;
    }

    public int getStockShopNum() {
        return stockShopNum;
    }

    public void setStockShopNum(int stockShopNum) {
        this.stockShopNum = stockShopNum;
    }

    public Date getStockShopDate() {
        return stockShopDate;
    }

    public void setStockShopDate(Date stockShopDate) {
        this.stockShopDate = stockShopDate;
    }

    public Double getStockShopTotalMoney() {
        return stockShopTotalMoney;
    }

    public void setStockShopTotalMoney(Double stockShopTotalMoney) {
        this.stockShopTotalMoney = stockShopTotalMoney;
    }

    public Double getStockSalePrice() {
        return stockSalePrice;
    }

    public void setStockSalePrice(Double stockSalePrice) {
        this.stockSalePrice = stockSalePrice;
    }

    public int getStockSaleNum() {
        return stockSaleNum;
    }

    public void setStockSaleNum(int stockSaleNum) {
        this.stockSaleNum = stockSaleNum;
    }

    public Double getStockSaleTotalMoney() {
        return stockSaleTotalMoney;
    }

    public void setStockSaleTotalMoney(Double stockSaleTotalMoney) {
        this.stockSaleTotalMoney = stockSaleTotalMoney;
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
        return "MyStock{" +
                "id=" + id +
                ", stockName='" + stockName + '\'' +
                ", stockNum='" + stockNum + '\'' +
                ", stockShopPrice=" + stockShopPrice +
                ", stockShopNum=" + stockShopNum +
                ", stockShopDate=" + stockShopDate +
                ", stockShopTotalMoney=" + stockShopTotalMoney +
                ", stockSalePrice=" + stockSalePrice +
                ", stockSaleNum=" + stockSaleNum +
                ", stockSaleTotalMoney=" + stockSaleTotalMoney +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

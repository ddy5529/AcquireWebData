package com.ddy.spide.acquire_web_data.dao;

import com.ddy.spide.acquire_web_data.model.StockMark;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 自定义的简单查询就是根据方法名来自动生成 SQL，
 * 主要的语法是findXXBy,readAXXBy,queryXXBy,countXXBy, getXXBy后面跟属性名称
 * 也使用一些加一些关键字And、 Or
 *
 * */
public interface StockMarkDao extends JpaRepository<StockMark,Integer> {
}

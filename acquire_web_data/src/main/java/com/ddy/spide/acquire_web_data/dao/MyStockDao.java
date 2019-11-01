package com.ddy.spide.acquire_web_data.dao;

import com.ddy.spide.acquire_web_data.model.MyStock;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

@Lazy
public interface MyStockDao extends JpaRepository<MyStock,Integer> {
}

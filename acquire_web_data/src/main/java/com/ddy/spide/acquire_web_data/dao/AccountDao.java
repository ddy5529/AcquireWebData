package com.ddy.spide.acquire_web_data.dao;

import com.ddy.spide.acquire_web_data.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao  extends JpaRepository<Account,Integer> {
}

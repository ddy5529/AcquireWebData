package com.ddy.spide.acquire_web_data.dao;

import com.ddy.spide.acquire_web_data.model.Account;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

@Lazy
public interface AccountDao  extends JpaRepository<Account,Integer> {
}

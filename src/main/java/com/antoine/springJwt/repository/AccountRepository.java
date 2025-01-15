package com.antoine.springJwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antoine.springJwt.model.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    List<Account> findByUserId(Integer userId);
}

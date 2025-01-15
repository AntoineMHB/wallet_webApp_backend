package com.antoine.springJwt.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antoine.springJwt.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    List<Transaction> findByAccountId(Integer accountId);
    List<Transaction> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
}

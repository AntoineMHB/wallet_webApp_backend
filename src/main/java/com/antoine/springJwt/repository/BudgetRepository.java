package com.antoine.springJwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antoine.springJwt.model.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer>{
    List<Budget> findByUserId(Integer userId);
    
}

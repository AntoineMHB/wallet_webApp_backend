package com.antoine.springJwt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.antoine.springJwt.model.Budget;
import com.antoine.springJwt.repository.BudgetRepository;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<Budget> getBudgetsByUser(Integer userId) {
        return budgetRepository.findByUserId(userId);
    }

    public Budget creaBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public void deleteBudget(Integer budgetId) {
        budgetRepository.deleteById(budgetId);
    }
    
}

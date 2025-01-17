package com.antoine.springJwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoine.springJwt.model.Expense;
import com.antoine.springJwt.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    
    // Add a new expense
      public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

     // Get all expenses
     public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get expenses by user ID
    public List<Expense> getExpensesByUserId(Integer userId) {
        return (List<Expense>) expenseRepository.findByUserId(userId);
    }

     // Getting expenses by account name
    public List<Expense> getExpenseByAccountName(String accountName) {
        return expenseRepository.findByAccountName(accountName);
    }

     // Update an expense
    //  public Expense updateExpense(Integer id, Expense updatedExpense) {
    //     return expenseRepository.findById(id).map(expense -> {
    //         expense.setAmount(updatedExpense.getAmount());
    //         expense.setDate(updatedExpense.getDate());
    //         expense.setDescription(updatedExpense.getDescription());
    //         expense.setCategory(updatedExpense.getCategory());
    //         expense.setUser(updatedExpense.getUser());
    //         return expenseRepository.save(expense);
    //     }).orElseThrow(() -> new RuntimeException("Expense not found with ID: " + id));
    // }

      // Delete an expense
      public void deleteExpense(Integer id) {
        expenseRepository.deleteById(id);
    }

     // Get an expense by its id
     public void getExpensesById(Integer id) {
        expenseRepository.getReferenceById(id);
    }


}

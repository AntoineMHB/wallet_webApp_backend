package com.antoine.springJwt.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoine.springJwt.model.Account;
import com.antoine.springJwt.model.Category;
import com.antoine.springJwt.model.Expense;
import com.antoine.springJwt.model.User;
import com.antoine.springJwt.repository.AccountRepository;
import com.antoine.springJwt.repository.CategoryRepository;
import com.antoine.springJwt.repository.ExpenseRepository;
import com.antoine.springJwt.repository.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private AccountRepository accountRepository;


    
   
    // Add a new expense
    public Expense addExpense(Expense expense) {
      Optional<User> userOpt = userRepository.findById(expense.getUser().getUser_id());
      Optional<Category> categoryOpt = categoryRepository.findById(expense.getCategory().getCategory_id());
      Optional<Account> accountOpt = accountRepository.findById(expense.getAccount().getAccount_id());

      if (userOpt.isPresent() && categoryOpt.isPresent() && accountOpt.isPresent()) {
          // Set the correct references to the expense
          expense.setUser(userOpt.get());
          expense.setCategory(categoryOpt.get());
          expense.setAccount(accountOpt.get());

          // Save the expense and return it
          return expenseRepository.save(expense);
      } else {
          throw new IllegalArgumentException("Invalid userId, categoryId, or accountId provided");
      }
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

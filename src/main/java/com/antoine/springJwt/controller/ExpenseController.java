package com.antoine.springJwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antoine.springJwt.model.Account;
import com.antoine.springJwt.model.Expense;
import com.antoine.springJwt.model.User;
import com.antoine.springJwt.service.AccountService;
import com.antoine.springJwt.service.ExpenseService;
import com.antoine.springJwt.service.UserService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final UserService userService;
    private final AccountService accountService;

     public ExpenseController( UserService userService, AccountService accountService) {
        this.accountService = accountService;
        this.userService = userService;
    }



    @Autowired
    private ExpenseService expenseService;
    
    
   // Add a new expense
   @PostMapping
   public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
       // Assuming the Expense object is populated with all necessary fields

    //    if (expense.getUser() == null || expense.getUser().getId() == null) {
    //         return ResponseEntity.badRequest().body(null); // User is mandatory
    //     }

        // Fetch the user from the db
        User user = userService.getUserById(expense.getUser().getId());
        // if (user == null) {
        //     return ResponseEntity.badRequest().body(null); // Invalid user
        // }
        expense.setUser(user);

        // Validate Account ID
    // if (expense.getAccount() == null || expense.getAccount().getAccount_id() == null) {
    //     return ResponseEntity.badRequest().body(null); // Account is mandatory
    // }

    // Fetch the account from the DB
    Account account = accountService.getAccountById(expense.getAccount().getAccount_id());
    // if (account == null) {
    //     return ResponseEntity.badRequest().body(null); // Invalid account
    // }
    expense.setAccount(account);
       Expense savedExpense = expenseService.addExpense(expense);
       return ResponseEntity.ok(savedExpense);
   }


       // Get all expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }


       // Get expenses by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Integer userId) {
        List<Expense> expenses = expenseService.getExpensesByUserId(userId);
        return ResponseEntity.ok(expenses);
    }

      // Get an expense by ID
      @GetMapping("/{id}")
      public ResponseEntity<Void> getExpenseById(@PathVariable Integer id) {
          expenseService.getExpensesById(id);
              
          return ResponseEntity.noContent().build();
      }

      // getting transactions by account name
    @GetMapping("/account/expenses/{accountName}")
    public ResponseEntity<List<Expense>> getExpenseByAccountName(@PathVariable String accountName) {
        return ResponseEntity.ok(expenseService.getExpenseByAccountName(accountName));
    }

         // Delete an expense
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Integer id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }


}

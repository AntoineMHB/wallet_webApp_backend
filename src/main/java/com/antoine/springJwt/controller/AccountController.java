package com.antoine.springJwt.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antoine.springJwt.model.Account;
import com.antoine.springJwt.model.User;
import com.antoine.springJwt.repository.UserRepository;
import com.antoine.springJwt.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    private final UserRepository userRepository;

    public AccountController(AccountService accountService, UserRepository userRepository) {
        this.accountService = accountService;
        this.userRepository = userRepository;
    }

    // private Integer getCurrentLoggedInUserId(){
    //     String userIdAString = SecurityContextHolder.getContext().getAuthentication().getName();
    //     return Integer.parseInt(userIdAString);
    // }

    // This method will get the currently logged-in user's ID (or username/email)
    private String getCurrentLoggedInUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Account>> getAccountsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(accountService.getAllAccountsByUser(userId));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        // Retrieve the currently logged-in user's ID from the SecurityContext
       // Integer loggedInUserId = getCurrentLoggedInUserId();

        // set the user object

        // Retrive the currently logged-in user's email (or username)
        String loggedInUserEmail = getCurrentLoggedInUserEmail();

        // Fetch the user object from the db based on the email (or username)
        User loggedInUser = userRepository.findByEmail(loggedInUserEmail)
             .orElseThrow(() -> new RuntimeException("User not found"));
        
        // set the user in the Account object
        account.setUser(loggedInUser);
        
        // here we create the account and return the response
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }


}

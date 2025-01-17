package com.antoine.springJwt.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antoine.springJwt.model.Transaction;
import com.antoine.springJwt.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionByAccount(@PathVariable Integer accountId) {
        return ResponseEntity.ok(transactionService.getTransactionByAccount(accountId));
    }

    // getting transactions by account name
    @GetMapping("/account/transactions/{accountName}")
    public ResponseEntity<List<Transaction>> getTransactionByAccountName(@PathVariable String accountName) {
        return ResponseEntity.ok(transactionService.getTransactionByAccountName(accountName));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(
        @RequestParam LocalDateTime startDate,
        @RequestParam LocalDateTime endDate
    ) {
        return ResponseEntity.ok(transactionService.getTransactionByDateRange(startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.createTransaction(transaction));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }

}

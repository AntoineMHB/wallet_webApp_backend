package com.antoine.springJwt.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.antoine.springJwt.model.Account;
import com.antoine.springJwt.model.Transaction;
import com.antoine.springJwt.repository.AccountRepository;
import com.antoine.springJwt.repository.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getTransactionByAccount(Integer accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public List<Transaction> getTransactionByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByDateBetween(startDate, endDate);
    }

    // Getting transactions by account name
    public List<Transaction> getTransactionByAccountName(String accountName) {
        return transactionRepository.findByAccountName(accountName);
    }

    public Transaction createTransaction(Transaction transaction) {
        // We load the account that already exists from th edb
        Account account = accountRepository.findById(transaction.getAccount().getAccount_id())
            .orElseThrow(() -> new RuntimeException("Account not found"));

        // we set the loaded Account to the transaction
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Integer transactionId) {
        transactionRepository.deleteById(transactionId);
    }
    
}

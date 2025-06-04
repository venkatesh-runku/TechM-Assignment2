package com.example.library.controller;

import com.example.library.model.Transaction;
import com.example.library.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issue")
    public Transaction issueBook(@RequestParam Long bookId, @RequestParam Long userId) {
        return transactionService.issueBook(bookId, userId);
    }

    @PostMapping("/return/{transactionId}")
    public Transaction returnBook(@PathVariable Long transactionId) {
        return transactionService.returnBook(transactionId);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getUserHistory(@PathVariable Long userId) {
        return transactionService.getUserHistory(userId);
    }

    @GetMapping("/overdue")
    public List<Transaction> getOverdueTransactions() {
        return transactionService.getOverdueTransactions();
    }
}

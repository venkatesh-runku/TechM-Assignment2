package com.example.library.repository;

import com.example.library.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserUserId(Long userId);

    @Query("SELECT t FROM Transaction t WHERE t.status = 'ISSUED' AND t.returnDate < CURRENT_DATE")
    List<Transaction> findOverdueTransactions();
}

package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Transaction;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.TransactionRepository;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public Transaction issueBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (book == null || user == null || !book.isAvailability()) {
            return null; // handle properly in controller
        }

        book.setAvailability(false);
        bookRepository.save(book);

        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setUser(user);
        transaction.setIssueDate(LocalDate.now());
        transaction.setReturnDate(LocalDate.now().plusDays(14)); // 2 weeks
        transaction.setStatus("ISSUED");

        return transactionRepository.save(transaction);
    }

    public Transaction returnBook(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if (transaction == null || !transaction.getStatus().equals("ISSUED")) {
            return null; // handle properly in controller
        }

        transaction.setStatus("RETURNED");
        transaction.getBook().setAvailability(true);
        bookRepository.save(transaction.getBook());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getUserHistory(Long userId) {
        return transactionRepository.findByUserUserId(userId);
    }

    public List<Transaction> getOverdueTransactions() {
        return transactionRepository.findOverdueTransactions();
    }
}

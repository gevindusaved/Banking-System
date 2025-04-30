package com.SasiyaNet.Banking.System.transaction;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    public List<Transaction> allTransactions() {
        return transactionsRepository.findAll();
    }

    public Optional<Transaction> singleTransaction(String transactionId) {
        return transactionsRepository.findByTransactionId(transactionId);
    }
}

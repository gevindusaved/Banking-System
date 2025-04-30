package com.SasiyaNet.Banking.System.transaction;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionsRepository extends MongoRepository<Transaction, String> {
    Optional<Transaction> findByTransactionId(String transactionId);

}

package com.SasiyaNet.Banking.System.transaction;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;

public interface TransactionsRepository extends MongoRepository<Transaction, ObjectId> {
    Optional<Transaction>findByTransactionId(String transactionId);
}

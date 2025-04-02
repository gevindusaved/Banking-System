package com.SasiyaNet.Banking.System;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;

public interface FixedDepositRepository extends MongoRepository<FixedDeposit, ObjectId> {
    Optional<FixedDeposit> findByFixedDepositId(String fixedDepositId);
}

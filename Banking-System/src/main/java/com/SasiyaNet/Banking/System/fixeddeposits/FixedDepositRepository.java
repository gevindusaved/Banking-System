package com.SasiyaNet.Banking.System.fixeddeposits;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;

public interface FixedDepositRepository extends MongoRepository<FixedDeposit, ObjectId> {
    // List<FixedDeposit> findByAccount_Id(String account_id);
    Optional<FixedDeposit> findByFixedDepositId(String fixedDepositId); // Add this line
}


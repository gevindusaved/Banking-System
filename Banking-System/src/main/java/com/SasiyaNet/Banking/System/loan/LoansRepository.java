package com.SasiyaNet.Banking.System.loan;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;

public interface LoansRepository extends MongoRepository<Loan, ObjectId> {
    Optional<Loan> findLoanByLoanId(String loanId);

}

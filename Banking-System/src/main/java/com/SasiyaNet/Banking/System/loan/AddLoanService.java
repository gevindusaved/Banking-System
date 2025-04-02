package com.SasiyaNet.Banking.System.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class AddLoanService {

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Loan createLoan(String loanId, Integer loanAmount, Integer interestRate, String dueDate, String status, String accountId) {
        Loan loan = new Loan(null, loanId, loanAmount, interestRate, dueDate, status, accountId);
        return loansRepository.save(loan);
    }
    

    public Loan updateLoan(String loanId, Integer loan_amount, Integer interest_rate, String due_date, String status,
            String account_id) {
        Query query = new Query(Criteria.where("loanId").is(loanId));
        Update update = new Update()
                .set("loan_amount", loan_amount)
                .set("interest_rate", interest_rate)
                .set("due_date", due_date)
                .set("status", status)
                .set("account_id", account_id);

        // Loan updatedLoan = mongoTemplate.findAndModify(query, update, Loan.class);
        Loan updatedLoan = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true),Loan.class);

        if (updatedLoan == null) {
            throw new RuntimeException("Loan not found for Loan ID: " + loanId);
        }

        return updatedLoan;
    }
}

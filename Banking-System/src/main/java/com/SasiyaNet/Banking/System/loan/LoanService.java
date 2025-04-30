package com.SasiyaNet.Banking.System.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Loan createLoan(String loanId, Integer loanAmount, Integer interestRate, String dueDate, String status, String accountId) {
        Loan loan = new Loan(null, loanId, loanAmount, interestRate, dueDate, status, accountId);
        return loansRepository.save(loan);
    }


    public List<Loan> getAllLoans() {
        return loansRepository.findAll();
    }

    public Optional<Loan> getLoanByIdAndUsername(String loanId, String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("loan_id").is(loanId).and("username").is(username));
        return Optional.ofNullable(mongoTemplate.findOne(query, Loan.class));
    }

    public ResponseEntity<String> processRepayment(String loanId, int amount, String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("loanId").is(loanId).and("username").is(username));
        Loan loan = mongoTemplate.findOne(query, Loan.class);

        if (loan == null) {
            return new ResponseEntity<>("Loan not found or not associated with this username", HttpStatus.NOT_FOUND);
        }

        if ("paid".equalsIgnoreCase(loan.getStatus())) {
            return new ResponseEntity<>("Loan already fully repaid", HttpStatus.BAD_REQUEST);
        }

        int newAmount = loan.getLoanAmount() - amount;
        Update update = new Update().set("loan_amount", newAmount);
        if (newAmount <= 0) {
            update.set("status", "paid");
        }

        mongoTemplate.updateFirst(query, update, Loan.class);
        return new ResponseEntity<>("Repayment successful", HttpStatus.OK);
    }
}

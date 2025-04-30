package com.SasiyaNet.Banking.System.loan;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/loans")
class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Map<String, Object> payload) {
        try {
            String loanId = (String) payload.get("loanId");
            int loanAmount = (int) payload.get("loan_amount");
            int interestRate = (int) payload.get("interest_rate");
            String dueDate = (String) payload.get("due_date");
            String status = (String) payload.get("status");
            String accountId = (String) payload.get("account_id");

            Loan loan = loanService.createLoan(loanId, loanAmount, interestRate, dueDate, status, accountId);
            return new ResponseEntity<>(loan, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable String loanId, @RequestParam String username) {
        Optional<Loan> loan = loanService.getLoanByIdAndUsername(loanId, username);
        return loan.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/repay")
    public ResponseEntity<String> repayLoan(@RequestBody Map<String, Object> payload) {
        String loanId = (String) payload.get("loanId");
        int amount = (int) payload.get("amount");
        String username = (String) payload.get("username");
        return loanService.processRepayment(loanId, amount, username);
    }

}

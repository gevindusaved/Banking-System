package com.SasiyaNet.Banking.System.transaction;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AddTransactionService addTransactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.allTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getSingleTransaction(@PathVariable String transactionId) {
        Optional<Transaction> transaction = transactionService.singleTransaction(transactionId);

        return transaction.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody Map<String, Object> payload) {
        try {
            int transactionAmount = Integer.parseInt(payload.get("transaction_amount").toString());
            String transactionType = (String) payload.get("transaction_type");
            String accountId = (String) payload.get("account_id");
            String username = (String) payload.get("username"); // make sure frontend sends this!
    
            Transaction transaction = addTransactionService.createTransactionAuto(
                transactionAmount, transactionType, accountId, username
            );
    
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid request data: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    

}

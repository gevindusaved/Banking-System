package com.SasiyaNet.Banking.System.account;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend to access this API
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AddAccountService addAccountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(accountService.allAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{accountId}/{username}")
    public ResponseEntity<Account> getSingleAccount(@PathVariable String accountId, @PathVariable String username) {
        Optional<Account> account = accountService.singleAccount(accountId, username);

        return account.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Map<String, Object> payload) {
        try {
            String username = (String) payload.get("username");
            int balance = (Integer) payload.get("balance");
            String account_type = (String) payload.get("account_type");
    
            Account account = addAccountService.createAccountAuto(username, balance, account_type);
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid request data: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
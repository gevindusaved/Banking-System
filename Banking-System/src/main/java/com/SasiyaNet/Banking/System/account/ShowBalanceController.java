package com.SasiyaNet.Banking.System.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/showbalance")
public class ShowBalanceController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/{accountId}/{username}")
    public ResponseEntity<?> getBalance(@PathVariable String accountId, @PathVariable String username) {
        Optional<Account> accountOptional = accountRepository.findByAccountIdAndUsername(accountId, username);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return ResponseEntity.ok().body("{\"balance\": " + account.getBalance() + "}");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
    }
}
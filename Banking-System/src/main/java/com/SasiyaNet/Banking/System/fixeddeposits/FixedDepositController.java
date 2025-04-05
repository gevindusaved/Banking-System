package com.SasiyaNet.Banking.System.fixeddeposits;

import org.springframework.web.bind.annotation.*;

import com.SasiyaNet.Banking.System.account.Account;
import com.SasiyaNet.Banking.System.account.AccountService;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/fdeposits")
public class FixedDepositController {

    @Autowired
    private FixedDepositService fixedDepositService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private AddFixedDepositService addFixedDepositService;

    @GetMapping
    public ResponseEntity<List<FixedDeposit>> getAllFixedDeposits() {
        return new ResponseEntity<>(fixedDepositService.allFixedDeposits(), HttpStatus.OK);
    }

    @GetMapping("/{fixedDepositId}/{username}")
    public ResponseEntity<FixedDeposit> getFixedDepositById(@PathVariable String fixedDepositId, @PathVariable String username) {
        Optional<FixedDeposit> fixedDeposit = fixedDepositService.getFixedDepositById(fixedDepositId);
    
        return fixedDeposit.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    

@PostMapping("/lookup")
public ResponseEntity<?> getFixedDepositSecure(@RequestBody Map<String, String> payload) {
    String fixedDepositId = payload.get("fixedDepositId");
    String username = payload.get("username");

    Optional<FixedDeposit> optionalFD = fixedDepositService.getFixedDepositById(fixedDepositId);
    if (optionalFD.isEmpty()) {
        return new ResponseEntity<>("Fixed Deposit not found.", HttpStatus.NOT_FOUND);
    }

    FixedDeposit fd = optionalFD.get();
    String accountId = fd.getAccount_id();

    Optional<Account> optionalAccount = accountService.singleAccount(accountId, username);
    if (optionalAccount.isEmpty()) {
        return new ResponseEntity<>("Access denied or account mismatch.", HttpStatus.UNAUTHORIZED);
    }

    return new ResponseEntity<>(fd, HttpStatus.OK);
}

}

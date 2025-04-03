package com.SasiyaNet.Banking.System;

import org.springframework.web.bind.annotation.*;

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
    private AddFixedDepositService addFixedDepositService;

    @GetMapping
    public ResponseEntity<List<FixedDeposit>> getAllFixedDeposits() {
        return new ResponseEntity<>(fixedDepositService.allFixedDeposits(), HttpStatus.OK);
    }

    @GetMapping("/{fixedDepositId}")
    public ResponseEntity<FixedDeposit> getFixedDepositById(@PathVariable String fixedDepositId) {
        Optional<FixedDeposit> fixedDeposit = fixedDepositService.getFixedDepositById(fixedDepositId);
    
        return fixedDeposit.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    

    @PostMapping
    public ResponseEntity<FixedDeposit> createFixedDeposit(@RequestBody Map<String, Object> payload) {
        try {
            String fixedDepositId = (String) payload.get("fixedDepositId");
            int deposit_amount = Integer.parseInt(payload.get("deposit_amount").toString()); 
            int interest_rate = (Integer) payload.get("interest_rate");
            String maturity_date = (String) payload.get("maturity_date");
            String account_id = (String) payload.get("account_id");

            FixedDeposit fixedDeposit = addFixedDepositService.createFixedDeposit(fixedDepositId, deposit_amount,
                    interest_rate, maturity_date, account_id);
            return new ResponseEntity<>(fixedDeposit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

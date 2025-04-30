package com.SasiyaNet.Banking.System.fixeddeposits;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FixedDepositService {

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

    public List<FixedDeposit> allFixedDeposits() {
        return fixedDepositRepository.findAll();
    }
    public Optional<FixedDeposit> getFixedDepositById(String fixedDepositId) {
        return fixedDepositRepository.findByFixedDepositId(fixedDepositId);
    }
    
    
    public Optional<FixedDeposit> singleFixedDeposit(String fixedDepositId) {
        return fixedDepositRepository.findByFixedDepositId(fixedDepositId);
    }

}
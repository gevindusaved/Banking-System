package com.SasiyaNet.Banking.System.account;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> allAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> singleAccount(String accountId, String username) {
        return accountRepository.findByAccountIdAndUsername(accountId, username);
    }

}

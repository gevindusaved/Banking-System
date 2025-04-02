package com.SasiyaNet.Banking.System.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class AddAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Account createAccount(String accountId, String username, Integer balance, String account_type) {

        Optional<Account> existingAccount = accountRepository.findByAccountIdAndUsername(accountId, username);

        if (existingAccount.isPresent()) {
            throw new RuntimeException("Account ID already exists for this user.");
        }

        Account account = new Account(accountId, username, balance, account_type);
        return accountRepository.save(account);
    }

    public void updateAccountBalance(String accountId, Integer amount, boolean isDeposit) {
        Query query = new Query(Criteria.where("accountId").is(accountId));
        Account account = mongoTemplate.findOne(query, Account.class);

        if (account == null) {
            throw new RuntimeException("Account not found for Account ID: " + accountId);
        }

        int updatedBalance = isDeposit ? account.getBalance() + amount : account.getBalance() - amount;

        boolean isSavingAccount = "savings".equalsIgnoreCase(account.getAccountType());

        if (!isDeposit && updatedBalance < 0) {
            throw new RuntimeException("Insufficient balance for account: " + accountId);
        }

        Update update = new Update().set("balance", updatedBalance);
        mongoTemplate.updateFirst(query, update, Account.class);
    }

}

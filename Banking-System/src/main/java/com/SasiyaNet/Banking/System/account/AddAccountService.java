package com.SasiyaNet.Banking.System.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
@Service
public class AddAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Account createAccount(String accountId, String username, Integer balance, String account_type, LocalDateTime createdAt) {

        Optional<Account> existingAccount = accountRepository.findByAccountIdAndUsername(accountId, username);

        if (existingAccount.isPresent()) {
            throw new RuntimeException("Account ID already exists for this user.");
        }

        Account account = new Account(accountId, username, balance, account_type, createdAt);
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
    public String checkAccount(String username, String account_id, String expectedAccountType) {
        // Step 1: Query account by both account ID and username to make sure it's the right user
        Query query = new Query(
            Criteria.where("accountId").is(account_id)
                    .and("username").is(username)
        );
        Account account = mongoTemplate.findOne(query, Account.class);
    
        if (account == null) {
            throw new RuntimeException("Account not found for given Account ID and Username.");
        }
    
        // Step 2: Check if account_type matches expected type (e.g., "fixed-deposit")
        if (!account.getAccountType().equalsIgnoreCase(expectedAccountType)) {
            throw new RuntimeException("Account is not of expected type: " + expectedAccountType);
        }
    
        return ("✅ Account validated: account ID matches, username matches, and type matches.");
    }
    public Account createAccountAuto(String username, Integer balance, String account_type) {
        String newAccountId = generateNextAccountId();
    
        Account account = new Account(newAccountId, username, balance, account_type, LocalDateTime.now());
        return accountRepository.save(account);
    }
    
    private String generateNextAccountId() {
        List<Account> allAccounts = accountRepository.findAll();
    
        String lastId = allAccounts.stream()
            .map(Account::getAccountId)
            .filter(id -> id != null && id.startsWith("accI_"))
            .max(Comparator.comparingInt(id -> Integer.parseInt(id.substring(5))))
            .orElse("accI_000");
    
        int nextNumber = Integer.parseInt(lastId.substring(5)) + 1;
        return String.format("accI_%03d", nextNumber);
    }
    
}

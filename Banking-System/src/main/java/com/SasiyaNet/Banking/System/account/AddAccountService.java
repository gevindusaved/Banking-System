package com.SasiyaNet.Banking.System.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.SasiyaNet.Banking.System.fixeddeposits.AddFixedDepositService;
import com.SasiyaNet.Banking.System.fixeddeposits.FixedDeposit;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class AddAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AddFixedDepositService addFixedDepositService;

    // public Account createAccount(String accountId, String username, Integer
    // balance, String account_type,
    // LocalDateTime createdAt) {

    // Optional<Account> existingAccount =
    // accountRepository.findByAccountIdAndUsername(accountId, username);

    // if (existingAccount.isPresent()) {
    // throw new RuntimeException("Account ID already exists for this user.");
    // }

    // Account account = new Account(accountId, username, balance, account_type,
    // createdAt);
    // return accountRepository.save(account);
    // }

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

    public Account createAccountAuto(String username, Integer balance, String account_type) {
        String newAccountId = generateNextAccountId();

        double interestRate = account_type.equalsIgnoreCase("savings") ? 6.0 : 13.0;

        balance += (int) (balance * (interestRate / 100.0));

        Account account = new Account(newAccountId, username, balance, account_type, LocalDateTime.now(), interestRate);
        Account savedAccount = accountRepository.save(account);

        if (account_type.equalsIgnoreCase("fixed deposit")) {
            String fixedDepositId = "FD_" + newAccountId;

            double fullAmount = balance + (balance * (interestRate / 100.0));

            // For demo, just use LocalDateTime.now() + 1 year
            String maturityDate = LocalDateTime.now().plusYears(1).toString();

            FixedDeposit fd = new FixedDeposit(null, fixedDepositId, balance, (int) interestRate, maturityDate,
                    newAccountId, fullAmount);
            addFixedDepositService.saveFixedDeposit(fd);
        }

        return savedAccount;
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

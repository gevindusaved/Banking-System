package com.SasiyaNet.Banking.System.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.SasiyaNet.Banking.System.account.AddAccountService;
import com.SasiyaNet.Banking.System.account.Account;

@Service
public class AddTransactionService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AddAccountService addAccountService;

    public Transaction createTransaction(String transactionId, Integer transaction_amount, String transaction_type,
            String transaction_date, String account_id, String username) {

        boolean isDeposit = transaction_type.equalsIgnoreCase("deposit");

        addAccountService.updateAccountBalance(account_id, transaction_amount, isDeposit);

        Transaction transaction = new Transaction(null, transactionId, transaction_amount, transaction_type,
                transaction_date, account_id, username);
        return transactionsRepository.save(transaction);
    }
}

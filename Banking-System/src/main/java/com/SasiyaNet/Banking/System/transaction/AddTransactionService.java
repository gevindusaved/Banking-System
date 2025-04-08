package com.SasiyaNet.Banking.System.transaction;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.SasiyaNet.Banking.System.account.AddAccountService;

@Service
public class AddTransactionService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AddAccountService addAccountService;

    // public Transaction createTransaction(String transactionId, Integer transaction_amount, String transaction_type,
    //         LocalDateTime createdAt, String account_id, String username) {

    //     boolean isDeposit = transaction_type.equalsIgnoreCase("deposit");

    //     addAccountService.updateAccountBalance(account_id, transaction_amount, isDeposit);

    //     Transaction transaction = new Transaction(null, transactionId, transaction_amount, transaction_type,
    //             createdAt, account_id, username);
    //     return transactionsRepository.save(transaction);
    // }

    public Transaction createTransactionAuto(Integer transaction_amount, String transaction_type,
                                         String account_id, String username) {

    boolean isDeposit = transaction_type.equalsIgnoreCase("deposit");
    addAccountService.updateAccountBalance(account_id, transaction_amount, isDeposit);

    String newTransactionId = generateNextTransactionId();
    LocalDateTime now = LocalDateTime.now();

    Transaction transaction = new Transaction(
        newTransactionId,
        transaction_amount,
        transaction_type,
        now,
        account_id,
        username
    );

    return transactionsRepository.save(transaction);
}


    private String generateNextTransactionId() {
        List<Transaction> allTransactions = transactionsRepository.findAll();

        String lastId = allTransactions.stream()
                .map(Transaction::getTransactionId)
                .filter(id -> id != null && id.startsWith("trrI_"))
                .max(Comparator.comparingInt(id -> Integer.parseInt(id.substring(5))))
                .orElse("trrI_000");

        int nextNumber = Integer.parseInt(lastId.substring(5)) + 1;
        return String.format("trrI_%03d", nextNumber);
    }

}

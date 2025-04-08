package com.SasiyaNet.Banking.System.transaction;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Document(collection = "transactions")
@Data
// @AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private String id; // MongoDB document ID (optional, if you need it)

    @Field("transaction_id")
    private String transactionId;

    @Field("amount")
    private Integer transaction_amount;

    @Field("transaction_type")
    private String transaction_type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field("createdAt")
    private LocalDateTime createdAt;

    @Field("account_id")
    private String account_id;

    @Field("username")
    private String username;

    public Transaction(String transactionId, Integer transaction_amount, String transaction_type,
                       LocalDateTime createdAt, String account_id, String username) {
        this.transactionId = transactionId;
        this.transaction_amount = transaction_amount;
        this.transaction_type = transaction_type;
        this.createdAt = createdAt;
        this.account_id = account_id;
        this.username = username;
    }


    // Getters & Setters
    // public String getId() {
    //     return id;
    // }

    // public void setId(String id) {
    //     this.id = id;
    // }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionAmount() {
        return transaction_amount;
    }

    public void setTransactionAmount(Integer transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransactionType() {
        return transaction_type;
    }

    public void setTransactionType(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getAccountId() {
        return account_id;
    }

    public void setAccountId(String account_id) {
        this.account_id = account_id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getters & Setters
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

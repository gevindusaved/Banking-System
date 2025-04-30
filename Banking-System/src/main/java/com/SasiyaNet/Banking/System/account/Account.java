package com.SasiyaNet.Banking.System.account;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private Double interest_rate; // Add this
    @Field("accountId")
    private String accountId;

    @Field("username")
    private String username;

    private Integer balance;
    private String account_type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field("createdAt")
    private LocalDateTime createdAt; // new field

    public Account(String accountId, String username, Integer balance, String account_type, LocalDateTime createdAt, Double interest_rate) {
        this.accountId = accountId;
        this.username = username;
        this.balance = balance;
        this.account_type = account_type;
        this.createdAt = createdAt;
        this.interest_rate = interest_rate;
    }
    
    // Add getter & setter
    public Double getInterest_rate() {
        return interest_rate;
    }
    public void setInterest_rate(Double interest_rate) {
        this.interest_rate = interest_rate;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return account_type;
    }

    public void setAccountType(String account_type) {
        this.account_type = account_type;
    }

    // Getters & Setters
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // (Other getters/setters unchanged)
}

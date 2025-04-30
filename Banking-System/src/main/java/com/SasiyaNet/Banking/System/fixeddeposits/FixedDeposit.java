package com.SasiyaNet.Banking.System.fixeddeposits;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "fixeddeposits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixedDeposit {
    @Id
    private ObjectId id;

    @Field("fixed_deposit_id")
    private String fixedDepositId;

    @Field("deposit_amount")
    private Integer deposit_amount;

    @Field("interest_rate")
    private Integer interest_rate;

    @Field("maturity_date")
    private String maturity_date;

    @Field("account_id")
    private String account_id;

    @Field("full_amount")
    private Double full_amount;

    @Field("current_balance")
    private Double current_balance;

    @Field("createdAt")
    private LocalDateTime createdAt;

    public FixedDeposit(ObjectId id, String fixedDepositId, Integer deposit_amount, int interest_rate,
            String maturity_date, String account_id, double full_amount, double current_balance,
            LocalDateTime createdAt) {
        this.id = id;
        this.fixedDepositId = fixedDepositId;
        this.deposit_amount = deposit_amount;
        this.interest_rate = interest_rate;
        this.maturity_date = maturity_date;
        this.account_id = account_id;
        this.full_amount = full_amount;
        this.current_balance = current_balance;
        this.createdAt = createdAt;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFixedDepositId() {
        return fixedDepositId;
    }

    public void setFixedDepositId(String fixedDepositId) {
        this.fixedDepositId = fixedDepositId;
    }

    public Integer getDeposit_amount() {
        return deposit_amount;
    }

    public void setDeposit_amount(Integer deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public Integer getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(Integer interest_rate) {
        this.interest_rate = interest_rate;
    }

    public String getMaturity_date() {
        return maturity_date;
    }

    public void setMaturity_date(String maturity_date) {
        this.maturity_date = maturity_date;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Double getFull_amount() {
        return full_amount;
    }

    public void setFull_amount(Double full_amount) {
        this.full_amount = full_amount;
    }

    public Double getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(Double current_balance) {
        this.current_balance = current_balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}

package com.SasiyaNet.Banking.System.loan;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    private ObjectId id;
    @Field("loan_id")
    private String loanId;
    private Integer loanAmount;
    private Integer interestRate;
    private String dueDate;
    private String status;
    @Field("account_id")
    private String accountId; // Fixing inconsistency

    Loan(Object object, String loanId, Integer loanAmount, Integer interestRate, String dueDate, String status, String accountId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Integer interestRate) {
        this.interestRate = interestRate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}

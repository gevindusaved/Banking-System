package com.SasiyaNet.Banking.System.loan;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Document(collection = "loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    private ObjectId id;
    @Field("loan_id")
    private String loanId;
    private Integer loan_amount;
    private Integer interest_rate;
    private String due_date;
    private String status;
    @Field("account_id")
    private String accountId; // Fixing inconsistency

    public Integer getLoanAmount() { return loan_amount; }
}

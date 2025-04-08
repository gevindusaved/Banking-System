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

    public String getFixedDepositId() {
        return fixedDepositId;
    }

    public void setFixedDepositId(String fixedDepositId) {
        this.fixedDepositId = fixedDepositId;
    }
}

package com.SasiyaNet.Banking.System.fixeddeposits;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}

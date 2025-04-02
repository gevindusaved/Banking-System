package com.SasiyaNet.Banking.System.transaction;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    private ObjectId id;

    @Field("transaction_id")
    private String transactionId;

    @Field("amount")
    private Integer transaction_amount;

    @Field("transaction_type")
    private String transaction_type;

    @Field("transaction_date")
    private String transaction_date;

    @Field("account_id")
    private String account_id;

    @Field("username")
    private String username;
}

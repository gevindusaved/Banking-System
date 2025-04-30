package com.SasiyaNet.Banking.System.fixeddeposits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class AddFixedDepositService {

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public FixedDeposit saveFixedDeposit(FixedDeposit fixedDeposit) {
        return fixedDepositRepository.save(fixedDeposit);
    }

    public FixedDeposit updateFixedDeposit(String fixedDepositId, Integer deposit_amount, Integer interest_rate,
            String maturity_date, String account_id) {
        Query query = new Query(Criteria.where("fixed_deposit_id").is(fixedDepositId));
        Update update = new Update()
                .set("deposit_amount", deposit_amount)
                .set("interest_rate", interest_rate)
                .set("maturity_date", maturity_date)
                .set("account_id", account_id);

        FixedDeposit updatedFixedDeposit = mongoTemplate.findAndModify(query, update, FixedDeposit.class);

        if (updatedFixedDeposit == null) {
            throw new RuntimeException("FixedDeposit not found for FixedDeposit ID: " + fixedDepositId);
        }

        return updatedFixedDeposit;
    }
}

package com.SasiyaNet.Banking.System.account;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByAccountIdAndUsername(String accountId, String username);

}
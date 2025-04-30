package com.SasiyaNet.Banking.System.login;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends MongoRepository<LoginUser, String> {
    Optional<LoginUser> findByUsername(String username);
}
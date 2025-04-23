package com.SasiyaNet.Banking.System.userinformation;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends MongoRepository<UserInformation, ObjectId> {
    Optional<UserInformation> findUserInformationByUserInformationId(String userInformationId);

}

package com.SasiyaNet.Banking.System.userinformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class AddUserInformationService {

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    // Manual creation
    public UserInformation createUserInformation(
            String userInformationId,
            String firstName,
            String middleName,
            String lastName,
            LocalDate date_of_birth,
            String gender,
            String nationality,
            String marital_status,
            String occupation,
            String employer,
            String mobile,
            String alternate_phone,
            String email,
            String address,
            String street,
            String city,
            String state,
            String zip,
            String country,
            String mailing_address,
            String id_type,
            String id_number,
            LocalDate id_issue_date,
            LocalDate id_expiry_date) {

        UserInformation userInformation = new UserInformation(
                null,
                userInformationId,
                firstName,
                middleName,
                lastName,
                date_of_birth,
                gender,
                nationality,
                marital_status,
                occupation,
                employer,
                mobile,
                alternate_phone,
                email,
                address,
                street,
                city,
                state,
                zip,
                country,
                mailing_address,
                id_type,
                id_number,
                id_issue_date,
                id_expiry_date,
                LocalDateTime.now());

        return userInformationRepository.save(userInformation);
    }

    // Update name fields only
    public UserInformation updateUserInformation(String userInformationId, String firstName, String lastName) {
        Query query = new Query(Criteria.where("userInformationId").is(userInformationId));
        Update update = new Update()
                .set("firstName", firstName)
                .set("lastName", lastName);

        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);
        return mongoTemplate.findAndModify(query, update, options, UserInformation.class);

    }

    // Auto-generated user ID creation
    public UserInformation createUserInformationAuto(
            String firstName,
            String middleName,
            String lastName,
            LocalDate date_of_birth,
            String gender,
            String nationality,
            String marital_status,
            String occupation,
            String employer,
            String mobile,
            String alternate_phone,
            String email,
            String address,
            String street,
            String city,
            String state,
            String zip,
            String country,
            String mailing_address,
            String id_type,
            String id_number,
            LocalDate id_issue_date,
            LocalDate id_expiry_date) {

        String newUserInformationId = generateNextUserInformationId();

        UserInformation userInformation = new UserInformation(
                null,
                newUserInformationId,
                firstName,
                middleName,
                lastName,
                date_of_birth,
                gender,
                nationality,
                marital_status,
                occupation,
                employer,
                mobile,
                alternate_phone,
                email,
                address,
                street,
                city,
                state,
                zip,
                country,
                mailing_address,
                id_type,
                id_number,
                id_issue_date,
                id_expiry_date,
                LocalDateTime.now());

        return userInformationRepository.save(userInformation);
    }

    private String generateNextUserInformationId() {
        List<UserInformation> allUserInformations = userInformationRepository.findAll();
    
        String lastId = allUserInformations.stream()
                .map(UserInformation::getUserInformationId)
                .filter(id -> id != null && id.startsWith("userID_") && id.matches("userID_\\d+"))
                .max(Comparator.comparingInt(id -> Integer.parseInt(id.substring(7))))
                .orElse("userID_000");
    
        int nextNumber = Integer.parseInt(lastId.substring(7)) + 1;
        return String.format("userID_%03d", nextNumber);
    }
    
}

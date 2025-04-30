package com.SasiyaNet.Banking.System.user;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class AddUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public User createUser(String username, String first_name, String middle_name, String last_name, String password, String email) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User(null, username, first_name, middle_name, last_name, hashedPassword, email);
        return userRepository.save(user);
    }

    public User updateUser(String username, String first_name, String last_name) {
        Query query = new Query(Criteria.where("username").is(username));
        Update update = new Update()
            .set("first_name", first_name)
            .set("last_name", last_name);
        
        return mongoTemplate.findAndModify(query, update, User.class);
    }
}

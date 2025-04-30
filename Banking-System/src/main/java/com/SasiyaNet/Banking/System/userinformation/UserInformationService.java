package com.SasiyaNet.Banking.System.userinformation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService {
    @Autowired
    private UserInformationRepository userInformationRepository;

    public List<UserInformation> allUserInformations() {
        return userInformationRepository.findAll();
    }

    public Optional<UserInformation> singleUserInformation(String userInformationId) {
        return userInformationRepository.findUserInformationByUserInformationId(userInformationId);
    }

}

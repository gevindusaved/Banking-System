package com.SasiyaNet.Banking.System.userinformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/userinformations")
public class UserInformationController {

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private AddUserInformationService addUserInformationService;

    @GetMapping
    public ResponseEntity<List<UserInformation>> getAllUserInformations() {
        return new ResponseEntity<>(userInformationService.allUserInformations(), HttpStatus.OK);
    }

    @GetMapping("/{userInformationId}")
    public ResponseEntity<Optional<UserInformation>> getSingleUserInformation(@PathVariable String userInformationId) {
        return new ResponseEntity<>(userInformationService.singleUserInformation(userInformationId), HttpStatus.OK);
    }
    

    @PostMapping
public ResponseEntity<UserInformation> createUserInformation(@RequestBody Map<String, String> payload) {
    try {
        UserInformation userInformation = addUserInformationService.createUserInformationAuto(
                payload.get("firstName"),
                payload.get("middleName"),
                payload.get("lastName"),
                LocalDate.parse(payload.get("date_of_birth")),
                payload.get("gender"),
                payload.get("nationality"),
                payload.get("marital_status"),
                payload.get("occupation"),
                payload.get("employer"),
                payload.get("mobile"),
                payload.get("alternate_phone"),
                payload.get("email"),
                payload.get("address"),
                payload.get("street"),
                payload.get("city"),
                payload.get("state"),
                payload.get("zip"),
                payload.get("country"),
                payload.get("mailing_address"),
                payload.get("id_type"),
                payload.get("id_number"),
                LocalDate.parse(payload.get("id_issue_date")),
                LocalDate.parse(payload.get("id_expiry_date"))
        );

        return new ResponseEntity<>(userInformation, HttpStatus.CREATED);
    } catch (Exception e) {
        e.printStackTrace(); // Helpful for debugging
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}

    
}
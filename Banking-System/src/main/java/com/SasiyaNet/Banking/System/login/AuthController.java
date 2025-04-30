package com.SasiyaNet.Banking.System.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<LoginUser> userOptional = userLoginRepository.findByUsername(request.getUsername());
        if (userOptional.isPresent()) {
            LoginUser loginUser = userOptional.get();
            if (BCrypt.checkpw(request.getPassword(), loginUser.getPassword())) {
                return ResponseEntity.ok(loginUser.getUsername());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}

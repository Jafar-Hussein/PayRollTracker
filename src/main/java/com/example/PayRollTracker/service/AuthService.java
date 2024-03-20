package com.example.PayRollTracker.service;

import com.example.PayRollTracker.model.AuthRequest;
import com.example.PayRollTracker.model.ResponseMessage;
import com.example.PayRollTracker.model.Roles;
import com.example.PayRollTracker.model.UserEntity;
import com.example.PayRollTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private Roles roles;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public ResponseEntity<?> register(AuthRequest authRequest) {
        try {
            Optional<UserEntity> existingUser = userRepo.findByUsername(authRequest.getUsername());
            if (existingUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Username already exists");
            }

            String encryptedPassword = passwordEncoder.encode(authRequest.getPassword());

            UserEntity user = new UserEntity();
            user.setUsername(authRequest.getUsername());
            user.setPassword(encryptedPassword);
            user.setRole(Roles.USER);

            UserEntity savedUser = userRepo.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Unable to register user");
        }
    }

    public ResponseEntity<ResponseMessage> login(AuthRequest authRequest) {
       try{
           Authentication auth = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
           String token = tokenService.generateJwt(auth);
           return ResponseEntity.ok(new ResponseMessage(token));

       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body(new ResponseMessage("Error: Username or password is incorrect"));
       }
    }

}

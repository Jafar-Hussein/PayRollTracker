package com.example.PayRollTracker.service;

import com.example.PayRollTracker.model.UserEntity;
import com.example.PayRollTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    //save user
    public ResponseEntity<?> saveUser(UserEntity userEntity) {
       if (userRepo.findByUsername(userEntity.getUsername()).isPresent()) {
           return ResponseEntity.badRequest().body("User with this username already exists");
       }
         userRepo.save(userEntity);
            return ResponseEntity.ok("User registered successfully");
    }

    //find by username
    public UserEntity findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    //find all users
    public List<UserEntity> findAllUsers() {
        if (userRepo.findAll().isEmpty()) return null;
        return userRepo.findAll();
    }

    // update user
    public Optional<UserEntity> updateUser(Long id,UserEntity userEntity) {
    Optional<UserEntity> existingUser = userRepo.findById(id);
    if (existingUser.isPresent()) {
        UserEntity userToUpadate = existingUser.get();
        userToUpadate.setUsername(userEntity.getUsername());
        userToUpadate.setPassword(userEntity.getPassword());
        userToUpadate.setRole(userEntity.getRole());
        return Optional.of(userRepo.save(userToUpadate));
    }else {
        return Optional.empty();
    }

    }

    //delete user
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new IllegalStateException("User with id " + id + " does not exist");
        }
        userRepo.deleteById(id);
    }

}

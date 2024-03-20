package com.example.PayRollTracker.service;

import com.example.PayRollTracker.model.Roles;
import com.example.PayRollTracker.model.SecurityUser;
import com.example.PayRollTracker.model.UserEntity;
import com.example.PayRollTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaUserService implements UserDetailsService {
private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepo.findByUsername(username);

        if (optionalUser.isPresent()) {
            // If a user with the given username exists, a new SecurityUser is created from the UserEntity and returned.
            return new SecurityUser(optionalUser.get());
        } else {
            // If a user with the given username does not exist, a UsernameNotFoundException is thrown.
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
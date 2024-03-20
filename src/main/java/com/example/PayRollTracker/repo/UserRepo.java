package com.example.PayRollTracker.repo;

import com.example.PayRollTracker.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);
   // find by username and password
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
    // find by username
    Optional<UserEntity> findByUsername(String username);
}

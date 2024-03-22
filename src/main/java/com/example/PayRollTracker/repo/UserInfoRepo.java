package com.example.PayRollTracker.repo;

import com.example.PayRollTracker.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
}

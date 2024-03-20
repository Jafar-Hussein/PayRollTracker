package com.example.PayRollTracker.repo;

import com.example.PayRollTracker.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepo extends JpaRepository<CheckIn, Long> {
}

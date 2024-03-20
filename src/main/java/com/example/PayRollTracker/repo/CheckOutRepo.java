package com.example.PayRollTracker.repo;

import com.example.PayRollTracker.model.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRepo extends JpaRepository<CheckOut, Long> {
}

package com.example.PayRollTracker.repo;

import com.example.PayRollTracker.model.PayRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayRollRepo extends JpaRepository<PayRoll, Long> {
    List<PayRoll> findByMonthAndYear(int currentMonth, int currentYear);
}

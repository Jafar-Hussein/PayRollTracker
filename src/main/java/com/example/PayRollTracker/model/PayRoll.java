package com.example.PayRollTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "payrolls")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayRoll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer month;
    private Integer year;
    private Integer totalHours;
    private BigDecimal totalEarnings;
    private BigDecimal totalDeductions;
    private BigDecimal netPay;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}

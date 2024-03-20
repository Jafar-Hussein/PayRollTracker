package com.example.PayRollTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String totalHours;
    private String totalEarnings;
    private String totalDeductions;
    private String netPay;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}

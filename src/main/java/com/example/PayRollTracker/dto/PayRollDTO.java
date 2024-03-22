package com.example.PayRollTracker.dto;

import com.example.PayRollTracker.model.PayRoll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayRollDTO {
    private Integer month;
    private Integer year;
    private Integer totalHours;
    private BigDecimal totalEarnings;
    private BigDecimal totalDeductions;
    private BigDecimal netPay;
    private Long userId;

}

package com.example.PayRollTracker.dto;

import com.example.PayRollTracker.model.PayRoll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayRollDTO {
    private Integer month;
    private Integer year;
    private String totalHours;
    private String totalEarnings;
    private String totalDeductions;
    private String netPay;
    private Long userId;

}

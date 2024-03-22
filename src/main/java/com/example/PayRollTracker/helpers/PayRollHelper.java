package com.example.PayRollTracker.helpers;

import com.example.PayRollTracker.dto.PayRollDTO;
import com.example.PayRollTracker.model.PayRoll;

import java.math.BigDecimal;
import java.util.List;

public class PayRollHelper {
    public static PayRollDTO calculatePayRoll(List<PayRoll> payRolls){
        PayRollDTO payRollDTO = new PayRollDTO();

        // calculate total hours
        int totalHours = calculateTotalHours(payRolls);
        payRollDTO.setTotalHours(totalHours);

        // calculate total earnings
        BigDecimal totalEarnings = calculateTotalEarnings(payRolls);
        payRollDTO.setTotalEarnings(totalEarnings);

        // calculate total deductions
        BigDecimal totalDeductions = calculateTotalDeductions(payRolls);
        payRollDTO.setTotalDeductions(totalDeductions);

        // calculate net pay
        BigDecimal netPay = totalEarnings.subtract(totalDeductions);
        payRollDTO.setNetPay(netPay);

        return payRollDTO;

    }

    private static BigDecimal calculateTotalDeductions(List<PayRoll> payRolls) {
        BigDecimal totalDeductions = BigDecimal.ZERO;
        for (PayRoll payRoll : payRolls) {
            totalDeductions = totalDeductions.add(payRoll.getTotalDeductions());
        }
     return totalDeductions;
    }

    private static BigDecimal calculateTotalEarnings(List<PayRoll> payRolls) {
        BigDecimal totalEarnings = BigDecimal.ZERO;
        for (PayRoll payRoll : payRolls) {
            totalEarnings = totalEarnings.add(payRoll.getTotalEarnings());
        }
        return totalEarnings;
    }

    private static int calculateTotalHours(List<PayRoll> payRolls) {
        int totalHours = 0;
        for (PayRoll payRoll : payRolls) {
            totalHours += payRoll.getTotalHours();
        }
        return totalHours;
    }
}

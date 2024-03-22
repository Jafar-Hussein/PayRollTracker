package com.example.PayRollTracker.service;

import com.example.PayRollTracker.dto.PayRollDTO;
import com.example.PayRollTracker.helpers.PayRollHelper;
import com.example.PayRollTracker.model.PayRoll;
import com.example.PayRollTracker.repo.PayRollRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayRollService {
    private final PayRollRepo payRollRepo;

    // get all payrolls
    public List<PayRollDTO> getAllPayRolls() {
        List<PayRoll> payRolls = payRollRepo.findAll();
        return payRolls.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // get current motnh payRoll
    public PayRollDTO getCurrentMonthPayRoll() {
        // get current month and year
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        // query the database for the current month payRoll
        List<PayRoll> payRolls = payRollRepo.findByMonthAndYear(currentMonth, currentYear);

        // calculate payRollDTO
        return PayRollHelper.calculatePayRoll(payRolls);
    }

    // helper method to convert PayRoll entity to PayRollDTO
    private PayRollDTO convertToDto(PayRoll payRoll) {
        // You can use modelMapper here if needed
        return new PayRollDTO(payRoll.getMonth(), payRoll.getYear(), payRoll.getTotalHours(),
                payRoll.getTotalEarnings(), payRoll.getTotalDeductions(), payRoll.getNetPay(), payRoll.getUser().getId());
    }




}

package com.example.PayRollTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
}

package com.example.PayRollTracker.dto;

import com.example.PayRollTracker.model.Roles;
import com.example.PayRollTracker.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private Roles role;
    private Long userInfoId;
}

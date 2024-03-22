package com.example.PayRollTracker.controller;

import com.example.PayRollTracker.dto.UserDTO;
import com.example.PayRollTracker.dto.UserInfoDTO;
import com.example.PayRollTracker.model.ResponseMessage;
import com.example.PayRollTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;

@PostMapping("saveInfo")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public ResponseEntity<?> saveUserInfo(@RequestBody UserInfoDTO userDTO) {
    if (userDTO == null) {
        return ResponseEntity.badRequest().body(new ResponseMessage("User information cannot be null"));
    }
    userService.addUserInfo(userDTO);
    return ResponseEntity.ok("User information saved successfully");
}

@PutMapping("changePass")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public ResponseEntity<?> changePassword(@RequestBody String oldPassword, @RequestBody String newPassword) {
    if (oldPassword == null || newPassword == null) {
        return ResponseEntity.badRequest().body(new ResponseMessage("Old password or new password cannot be null"));
    }
    return ResponseEntity.ok(userService.changePassword(oldPassword, newPassword));

}
@PutMapping("updateInfo")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public ResponseEntity<?> updateInfo(@RequestBody UserDTO userDTO) {
    if (userDTO == null) {
        return ResponseEntity.badRequest().body(new ResponseMessage("User information cannot be null"));

    }
    return ResponseEntity.ok(userService.updateUserInfo(userDTO));
}

}

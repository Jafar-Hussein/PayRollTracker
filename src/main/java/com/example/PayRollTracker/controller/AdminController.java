package com.example.PayRollTracker.controller;

import com.example.PayRollTracker.dto.UserDTO;
import com.example.PayRollTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveUser(UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PostMapping("name")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findByName(@RequestBody String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PostMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}

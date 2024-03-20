package com.example.PayRollTracker.controller;

import com.example.PayRollTracker.model.AuthRequest;
import com.example.PayRollTracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
            return authService.register(authRequest);
        }

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
            return authService.login(authRequest);
        }
}

package com.gym.app.controller;

import com.gym.app.service.dto.AuthRequest;
import com.gym.app.configuration.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest req) {
        return ResponseEntity.ok(authService.register(req.getUsername(), req.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest req) {
        return ResponseEntity.ok(authService.login(req.getUsername(), req.getPassword()));
    }
}



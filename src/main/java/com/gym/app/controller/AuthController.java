package com.gym.app.controller;

import com.gym.app.baseframework.exception.SystemException;
import com.gym.app.service.dto.AuthRequest;
import com.gym.app.configuration.AuthService;

import com.gym.app.service.dto.RegistrationResponse;
import com.gym.app.service.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody AuthRequest req) throws SystemException {
        logger.info("AuthRequest for Registration : Received");
        return new ResponseEntity<>(authService.register(req.getUsername(), req.getPassword()), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthRequest req) throws SystemException{
        logger.info("User Details for Authentication:Received");
        return new ResponseEntity<>(authService.login(req.getUsername(), req.getPassword()), HttpStatus.OK);
    }
}



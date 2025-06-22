package com.gym.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/protected")
    public String getProtectedData() {
        return "🎉 Access granted! You are authenticated!";
    }
}

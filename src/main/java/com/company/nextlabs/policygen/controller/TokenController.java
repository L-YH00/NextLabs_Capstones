package com.company.nextlabs.policygen.controller;

import com.company.nextlabs.policygen.nextlabs.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    private final AuthenticationManager authManager;

    public TokenController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @GetMapping("/test-token")
    public String testToken() {
        try {
            String token = authManager.getAccessToken();
            return "Access Token: " + token;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

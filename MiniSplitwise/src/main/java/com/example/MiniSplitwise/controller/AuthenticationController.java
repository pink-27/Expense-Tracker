package com.example.MiniSplitwise.controller;

import com.example.MiniSplitwise.dto.AuthenticationRequest;
import com.example.MiniSplitwise.dto.AuthenticationResponse;
import com.example.MiniSplitwise.dto.RegisterRequest;
import com.example.MiniSplitwise.utils.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        System.out.println("Testing auth");
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateRequest(@RequestBody AuthenticationRequest request)
            throws Exception {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
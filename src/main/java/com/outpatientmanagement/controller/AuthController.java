package com.outpatientmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outpatientmanagement.dto.request.ForgotPasswordRequest;
import com.outpatientmanagement.dto.request.LoginRequest;
import com.outpatientmanagement.dto.request.ResetPasswordRequest;
import com.outpatientmanagement.dto.request.UserRequest;
import com.outpatientmanagement.dto.response.LoginResponse;
import com.outpatientmanagement.security.JwtUtil;
import com.outpatientmanagement.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
 
    private static final Logger logger =
            LoggerFactory.getLogger(AuthController.class);
 
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthService authService;
 
    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          AuthService authService) {
 
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }
 
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
    		@Valid @RequestBody UserRequest request) {
 
        authService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }
 
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
    		@Valid @RequestBody LoginRequest request) {
 
    		authenticationManager.authenticate(
    		    new UsernamePasswordAuthenticationToken(
    		        request.getUsername(),
    		        request.getPassword()
    		    )
    		);
    		 
    		String token = jwtUtil.generateToken(request.getUsername());
 
        LoginResponse response = authService.login(request);
 
        return ResponseEntity.ok(response);
    }
 
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
    		@Valid @RequestHeader("Authorization") String authHeader) {
 
        logger.info("Logout request received");
 
        return ResponseEntity.ok("Logged out successfully");
    }
 
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
    		@Valid @RequestBody ForgotPasswordRequest request) {
 
        authService.forgotPassword(request.getEmail());
        return ResponseEntity.ok("Password reset link sent");
    }
 
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
    		@Valid @RequestBody ResetPasswordRequest request) {
     
        authService.resetPassword(
                request.getUserId(),
                request.getUsername(),
                request.getNewPassword()
        );
     
        return ResponseEntity.ok("Password reset successful");
    }
}
 
package com.outpatientmanagement.service;

import org.springframework.http.ResponseEntity;

import com.outpatientmanagement.dto.request.LoginRequest;
import com.outpatientmanagement.dto.request.UserRequest;
import com.outpatientmanagement.dto.response.LoginResponse;

import jakarta.validation.Valid;


public interface AuthService {

	void registerUser(UserRequest request);

	void forgotPassword(String email);

	void resetPassword(Long userId, String username, String newPassword);

	LoginResponse login(@Valid LoginRequest request);
}

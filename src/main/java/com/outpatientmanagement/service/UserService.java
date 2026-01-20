package com.outpatientmanagement.service;

import org.springframework.http.ResponseEntity;

import com.outpatientmanagement.dto.request.UserRequest;
import com.outpatientmanagement.dto.response.UserResponse;

public interface UserService {

	ResponseEntity<?> createUser(UserRequest request);

	ResponseEntity<?> getUserById(Long id);

	ResponseEntity<?> updateUser(Long id, UserRequest request);

	ResponseEntity<?> deleteUser(Long id);

	ResponseEntity<?> logout();

	ResponseEntity<?> resetPassword(String username, String newPassword);







}

package com.outpatientmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outpatientmanagement.dto.request.UserRequest;
import com.outpatientmanagement.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest request)
	{
		logger.info("Create user request received");
		return userService.createUser(request);
	}
	@PreAuthorize("hasAnyRole('ADMIN','PATIENT','DOCTOR')")
	@GetMapping("/{id}")
	public ResponseEntity<?>getUserById(@PathVariable Long id)
	{
		logger.info("Fetched User");
		return userService.getUserById(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@Valid @PathVariable Long id, @RequestBody UserRequest request)
	{
		logger.info("Updated user with id:",id);
		return userService.updateUser(id,request);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id)
	{
		logger.info("Delete user with id: ",id);
		return userService.deleteUser(id);
	}
	
	
}

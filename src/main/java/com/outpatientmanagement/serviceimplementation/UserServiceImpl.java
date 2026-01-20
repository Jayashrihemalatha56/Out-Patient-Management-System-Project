package com.outpatientmanagement.serviceimplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.outpatientmanagement.dto.request.UserRequest;
import com.outpatientmanagement.dto.response.UserResponse;
import com.outpatientmanagement.entity.User;
import com.outpatientmanagement.exception.InvalidRequestException;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.mapper.UserMapper;
import com.outpatientmanagement.repository.UserRepository;
import com.outpatientmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	private static final Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

	public UserServiceImpl(UserRepository userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ResponseEntity<?> createUser(UserRequest request) {
		User user=userMapper.toRequest(request);
		User savedUser=userRepo.save(user);
		
		UserResponse response=userMapper.toResponse(savedUser);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> getUserById(Long id) {
		logger.info("Fetching user with id: ",id);
		
		User user=userRepo.findById(id)
				.orElseThrow(()->new ModelNotFoundException("User not found"));
		
		return ResponseEntity.ok(userMapper.toResponse(user));
	}

	@Override
	public ResponseEntity<?> updateUser(Long id, UserRequest request) {
		logger.info("Updating user with id: ",id);
		
		User user=userRepo.findById(id)
				.orElseThrow(()->new InvalidRequestException("User updation failed"));
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setRole(request.getRole());
		user.setActive(request.getActive());
		
		User updateUser=userRepo.save(user);
		return ResponseEntity.ok(userMapper.toResponse(updateUser));
	}

	@Override
	public ResponseEntity<?> deleteUser(Long id) {
		logger.info("Deleting user with id: ",id);
		
		if(!userRepo.existsById(id))
		{
			throw new ModelNotFoundException("User not found");
		}
		userRepo.deleteById(id);
		return ResponseEntity.ok("User deleted successfully");
	}

	@Override
	public ResponseEntity<?> logout() {
		return ResponseEntity.ok("Logout successful");
	}

	@Override
	public ResponseEntity<?> resetPassword(String username, String newPassword) {
		User user=userRepo.findByUsername(username)
				.orElseThrow(()->new ModelNotFoundException("User not found"));
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepo.save(user);
		return ResponseEntity.ok("Password reseted Successful");
	}
	
	

}

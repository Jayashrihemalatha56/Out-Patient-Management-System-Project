package com.outpatientmanagement.serviceimplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.outpatientmanagement.dto.request.LoginRequest;
import com.outpatientmanagement.dto.request.UserRequest;
import com.outpatientmanagement.dto.response.LoginResponse;
import com.outpatientmanagement.entity.User;
import com.outpatientmanagement.exception.InvalidRequestException;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.repository.UserRepository;
import com.outpatientmanagement.security.JwtUtil;
import com.outpatientmanagement.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
 
    private static final Logger logger =
            LoggerFactory.getLogger(AuthServiceImpl.class);
 
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
 
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
 
    @Override
    public void registerUser(UserRequest request) {
 
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new InvalidRequestException("Email already exists");
        }
 
        User user = new User();
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhoneNo(request.getPhoneNo());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(request.getActive());
 
        userRepository.save(user);
    }
 
    @Override
    public LoginResponse login(LoginRequest request) {
     
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ModelNotFoundException("User not found"));
     
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidRequestException("Invalid password");
        }
     
        String token = jwtUtil.generateToken(user.getUsername());
     
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setRole(user.getRole());
     
        return response;
    }
    @Override
    public void forgotPassword(String email) {
 
        User user = userRepository.findByEmail(email);
 
        if (user == null) {
            throw new InvalidRequestException(("Password incorrect"));
        }
 
        logger.info("Forgot password requested for {}", email);
    }

    @Override
    public void resetPassword(Long userId, String username, String newPassword) {
     
        if (userId == null || username == null || newPassword == null) {
            throw new InvalidRequestException("Invalid reset password request");
        }
     
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
     
        if (!user.getUsername().equals(username)) {
            throw new InvalidRequestException("Username does not match userId");
        }
     
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
     
        logger.info("Password reset successful for userId {}", userId);
    }
}
 
package com.outpatientmanagement.mapper;

import org.springframework.stereotype.Component;

import com.outpatientmanagement.dto.request.UserRequest;
import com.outpatientmanagement.dto.response.UserResponse;
import com.outpatientmanagement.entity.User;

@Component
public class UserMapper {

	public User toRequest(UserRequest request)
	{
		User user=new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setRole(request.getRole());
		user.setActive(true);
		return user;
	}
	
	public UserResponse toResponse(User user)
	{
		UserResponse response=new UserResponse();
		response.setId(user.getId());
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole());
		response.setActive(user.getActive());
		return response;
	}
}

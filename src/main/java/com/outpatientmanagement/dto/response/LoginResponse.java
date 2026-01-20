package com.outpatientmanagement.dto.response;

import com.outpatientmanagement.enums.Role;

public class LoginResponse {

	private String token;
	private Role role;

	public LoginResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}

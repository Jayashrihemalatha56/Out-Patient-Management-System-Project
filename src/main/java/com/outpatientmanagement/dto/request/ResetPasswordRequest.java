package com.outpatientmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ResetPasswordRequest {

    @NotNull(message = "User ID is required")
    private Long userId;
 
    @NotBlank(message = "Username is required")
    private String username;
 
    @NotBlank(message = "New password is required")
    @Size(min = 6, message = "Password must be minimum 6 characters")
    private String newPassword;
	
	public ResetPasswordRequest() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	

	
	
	
}

package com.outpatientmanagement.dto.request;

import com.outpatientmanagement.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {

	 @NotBlank(message = "Full name is required")
	 private String fullName;
	 
	 @NotBlank(message = "Username is required")
	 private String username;
	 
	 @NotBlank(message = "Password is required")
	 @Size(min = 6, message = "Password must be minimum 6 characters")
	 private String password;
	 
	 @NotBlank(message = "Phone number is required")
	 @Pattern(
			regexp = "^[6-9]\\d{9}$",
			message = "Invalid phone number"
			 )
	private String phoneNo;
	 
	@NotNull(message = "Role is required")
	private Role role;
	 
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
	 
	@NotNull(message = "Active status is required")
	private Boolean active;
	

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}

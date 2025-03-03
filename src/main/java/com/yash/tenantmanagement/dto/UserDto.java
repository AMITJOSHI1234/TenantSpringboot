package com.yash.tenantmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Long userId;
	
	@NotBlank(message = "UserName is required!!!")
	private String username;
	
	@NotBlank(message = "Password is required!!!")
	private String password;
	
	@NotBlank(message = "Email is required!!!")
	@Email(message = "Invalid email address!!!")
	private String email;
	
	@NotBlank(message = "Phone is required!!!")
	private String phone;
	
	@NotBlank(message = "address is required!!!")
	private String address;
	
	private Long roleId;
	
	private String roleName;
	
	private String token;
}

package com.yash.tenantmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

	@NotBlank(message = "Email is required!!!")
	@Email(message = "Invalid email address!!!")
	private String email;
	
	@NotBlank(message = "Password is required!!!")
	private String password;
}

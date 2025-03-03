package com.yash.tenantmanagement.controller;

/**
* This is a Login controller containing login and logout functionality
* @author amit joshi
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.tenantmanagement.dto.LoginDto;
import com.yash.tenantmanagement.dto.UserDto;
import com.yash.tenantmanagement.jwtconfig.JwtHelper;
import com.yash.tenantmanagement.jwtconfig.UserDetailService;
import com.yash.tenantmanagement.service.UserServiceInt;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("Login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

	private UserServiceInt userService;

	public LoginController(UserServiceInt userService) {
		this.userService = userService;
	}
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private JwtHelper helper;
	
	/*This is a controller for register user*/
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto dto){
		UserDto registerUser = this.userService.registerUser(dto);
		return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
	}
	
	/*This is a controller for login user*/
	@PostMapping("/login")
	public ResponseEntity<UserDto> loginUser(@Valid @RequestBody LoginDto dto,HttpSession session){
		UserDto loginUser = this.userService.authenticateUser(dto.getEmail(), dto.getPassword());
		if(loginUser!=null) {
			session.setAttribute("username", loginUser.getUsername());
			session.setAttribute("userId", loginUser.getUserId());
			session.setAttribute("roleId", loginUser.getRoleId());
			
			//generating token and setting token in userDto
			System.out.println("Inside loginMethod!!!");
			System.out.println("email:"+dto.getEmail());
			UserDetails userDetails = this.userDetailService.loadUserByUsername(loginUser.getEmail());
			String token = this.helper.generateToken(userDetails);
			loginUser.setToken(token);
			
			return ResponseEntity.ok(loginUser);
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	
}

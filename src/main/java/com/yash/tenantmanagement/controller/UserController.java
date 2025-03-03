package com.yash.tenantmanagement.controller;
/**
* This is a User controller containing login and logout functionality
* @author amit joshi
*/
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.tenantmanagement.dto.UserDto;
import com.yash.tenantmanagement.service.UserServiceInt;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Users")
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	private UserServiceInt userService;

	public UserController(UserServiceInt userService) {
		this.userService = userService;
	}
	
	/*This controller  is used to get all users*/
	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDto>> getAllUsers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "username") String sort
			){
		List<UserDto> users = this.userService.getAllUsers(page,size,sort);
		return ResponseEntity.ok(users);
	}
	
	/*This controller is used to update user with given id*/
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable Long id
			){
		UserDto dto = this.userService.updateUser(userDto,id);
		return new ResponseEntity<UserDto>(dto,HttpStatus.CREATED);
	}
	
	/*This controller is used to deleteUser with id*/
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		this.userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
	
	/*This controller is used to deleteUser with id*/
	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
		UserDto userDto = this.userService.getUserById(id);
		return ResponseEntity.ok(userDto);
	}
	
	/*This controller is used to search user*/
	@GetMapping("/searchUser")
	public ResponseEntity<List<UserDto>> searchUser(
			@RequestParam String username,
			@RequestParam String address
			){
		List<UserDto> users = this.userService.searchUser(username, address);
		return ResponseEntity.ok(users);
	}
	
	
}

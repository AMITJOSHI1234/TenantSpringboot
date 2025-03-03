package com.yash.tenantmanagement.service;

import java.util.List;

import com.yash.tenantmanagement.dto.UserDto;

public interface UserServiceInt {

	UserDto registerUser (UserDto userDto);
	
	UserDto authenticateUser(String email,String password);
	
	List<UserDto> getAllUsers(int page,int size,String sort);
	
	UserDto updateUser(UserDto userDto,Long userId);
	
	void deleteUser(Long id);
	
	UserDto getUserById(Long id);
	
	List<UserDto> searchUser(String username,String address);
}

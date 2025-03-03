package com.yash.tenantmanagement.utility;

import com.yash.tenantmanagement.domain.User;
import com.yash.tenantmanagement.dto.UserDto;

public class Mapping {

	public static UserDto mapToUserDto(User user) {
		UserDto dto = new UserDto();
		dto.setUserId(user.getUserId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setPhone(user.getPhone());
		dto.setAddress(user.getAddress());
		dto.setRoleId(user.getRoleId());
		dto.setRoleName(user.getRoleName());
		
		return dto;
	}
	
	
	public static User mapToUser(UserDto dto) {
		User user = new User();
		user.setUserId(dto.getUserId());
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setPhone(dto.getPhone());
		user.setAddress(dto.getAddress());
		user.setRoleId(2L);
		user.setRoleName("user");
		
		return user;
		
	}
}

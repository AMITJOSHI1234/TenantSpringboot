package com.yash.tenantmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yash.tenantmanagement.Exception.UserNotFoundException;
import com.yash.tenantmanagement.dao.UserRepository;
import com.yash.tenantmanagement.domain.User;
import com.yash.tenantmanagement.dto.UserDto;
import com.yash.tenantmanagement.utility.Mapping;

@Service
public class UserServiceImpl implements UserServiceInt {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = Mapping.mapToUser(userDto);
		User savedUser = this.userRepository.save(user);
		return Mapping.mapToUserDto(savedUser);
	}

	@Override
	public UserDto authenticateUser(String email, String password) {
		User user = this.userRepository.findByEmail(email);
		if(user!=null && user.getPassword().equals(password)) {
			return Mapping.mapToUserDto(user);
		}
		
		return null;
	}

	@Override
	public List<UserDto> getAllUsers(int page,int size,String sort) {
	    Page<User> users = this.userRepository.findAll(PageRequest.of(page, size,Sort.by(sort)));
		List<UserDto> userList = users.stream().map((user)->Mapping.mapToUserDto(user)).toList();
		return userList;
	}

	@Override
	public UserDto updateUser(UserDto userDto,Long userId) {
		User user = this.userRepository.findById(userId)
		.orElseThrow(()->new UserNotFoundException("user not found at this id!!!!"));
		
		//Save updated userD
		//user.setUserId(userDto.getUserId());
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAddress(userDto.getAddress());
		user.setPhone(userDto.getPhone());
		user.setRoleId(2L);
		user.setRoleName("user");
		
		User updated = this.userRepository.save(user);
		return Mapping.mapToUserDto(updated);
	}

	@Override
	public void deleteUser(Long id) {
		User user = this.userRepository.findById(id)
		.orElseThrow(()->new UserNotFoundException("user not found at this id!!!"));
		
		this.userRepository.delete(user);
		
	}

	@Override
	public UserDto getUserById(Long id) {
		User user = this.userRepository.findById(id)
		.orElseThrow(()->new UserNotFoundException("user not found at this id!!!"));
		
		return Mapping.mapToUserDto(user);
	}

	@Override
	public List<UserDto> searchUser(String username, String address) {
		List<User> users = this.userRepository.findByUsernameContainingAndAddressContaining(username, address);
		
	   return users.stream().map((user)->Mapping.mapToUserDto(user))
			   .toList();
	}

}

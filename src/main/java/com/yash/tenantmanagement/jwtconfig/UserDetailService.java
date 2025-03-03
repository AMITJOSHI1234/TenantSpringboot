package com.yash.tenantmanagement.jwtconfig;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yash.tenantmanagement.Exception.UserNotFoundException;
import com.yash.tenantmanagement.dao.UserRepository;
import com.yash.tenantmanagement.domain.User;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.findByEmail(username);
		if(user==null) {
			throw new UserNotFoundException("User not found");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
				Collections.EMPTY_LIST);
	}

}

package com.yash.tenantmanagement.dao;

/**
* This is a Repository for User
* @author amit joshi
*/
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.tenantmanagement.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	
	List<User> findByUsernameContainingAndAddressContaining(String username,String address);

}

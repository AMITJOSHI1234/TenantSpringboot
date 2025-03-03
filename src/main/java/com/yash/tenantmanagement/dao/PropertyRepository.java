package com.yash.tenantmanagement.dao;
/**
* This is a Repository for property
* @author amit joshi
*/

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.tenantmanagement.domain.Property;
import com.yash.tenantmanagement.dto.PropertyDto;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByAddress(String address);
	
}

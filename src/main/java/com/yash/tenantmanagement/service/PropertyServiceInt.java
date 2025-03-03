package com.yash.tenantmanagement.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yash.tenantmanagement.domain.Property;
import com.yash.tenantmanagement.dto.PropertyDto;

public interface PropertyServiceInt {

	PropertyDto saveProperty(PropertyDto propertyDto,MultipartFile file) throws IOException;
	
	PropertyDto getProperty(Long propertyId);
	
	Property getImage(Long propertyId);
	
	List<PropertyDto> getAllProperties();
	
	PropertyDto updateProperty(PropertyDto propertyDto,Long propertyId);
	
	void deleteProperty(Long propertyId);
	
	List<PropertyDto> searchPropertiesByAddress(String address);
	
}

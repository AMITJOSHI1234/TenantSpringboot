package com.yash.tenantmanagement.controller;

/**
* This is a Property controller containing login and logout functionality
* @author amit joshi
*/
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.tenantmanagement.domain.Property;
import com.yash.tenantmanagement.dto.PropertyDto;
import com.yash.tenantmanagement.dto.UserDto;
import com.yash.tenantmanagement.service.PropertyServiceInt;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@RestController
@RequestMapping("/Properties")
//@CrossOrigin(origins = "http://localhost:3000")
public class PropertyController {

	@Autowired
	private PropertyServiceInt propertyService;
	
	@Autowired
	private Validator validator;
	
	/*This controller is used to handle multi-part form*/
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<PropertyDto> createProperty(@Valid @RequestParam("property") String propertyJson,
			@RequestParam("file") MultipartFile file
			) throws IOException{
		
	ObjectMapper objectMapper = new ObjectMapper();
	PropertyDto propertyDto = objectMapper.readValue(propertyJson, PropertyDto.class);
	
	//Manually validate the propertyDto
	Set<ConstraintViolation<PropertyDto>> violations = validator.validate(propertyDto);
	if(!violations.isEmpty()) {
		throw new ConstraintViolationException(violations);
	}
	
	PropertyDto saveProperty = this.propertyService.saveProperty(propertyDto, file);
	return new ResponseEntity<PropertyDto>(saveProperty,HttpStatus.CREATED);
	}
	
	/*This is used to getProperty controller*/
	@GetMapping("getProperty/{propertyId}")
	public ResponseEntity<PropertyDto> getProperty(@PathVariable Long propertyId){
		PropertyDto property = this.propertyService.getProperty(propertyId);
		return ResponseEntity.ok(property);
	}
	
	/*This is used to get property image*/
	@GetMapping("propertyImage/{propertyId}")
	public ResponseEntity<byte[]> getPropertyImage(@PathVariable Long propertyId){
		Property property = this.propertyService.getImage(propertyId);
		byte[] image = property.getProperty_image().getData();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(image,headers,HttpStatus.OK);
	}
	
	/*This controller is used to get all property*/
	@GetMapping("getAllProperty")
	public ResponseEntity<List<PropertyDto>> getAllProperties(){
		return ResponseEntity.ok(propertyService.getAllProperties());
	}
	
	/*This controller is used to update property from id*/
	@PutMapping("updateProperty/{id}")
	public ResponseEntity<PropertyDto> updateProperty(@Valid @RequestBody PropertyDto dto,
			@PathVariable Long id){
		PropertyDto updateProperty = this.propertyService.updateProperty(dto, id);
		return ResponseEntity.ok(updateProperty);
	}
	/*
	 * 
	 * This controller is used to delete property with given id
	 * */
	@DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
	
	/*
	 * This controller is used for search property
	 * */
	@GetMapping("/searchProperty")
    public ResponseEntity<List<PropertyDto>> searchPropertiesByAddress(@RequestParam String address) {
        List<PropertyDto> properties = propertyService.searchPropertiesByAddress(address);
        return ResponseEntity.ok(properties);
    }
}

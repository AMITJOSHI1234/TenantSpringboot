package com.yash.tenantmanagement.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.tenantmanagement.dao.PropertyRepository;
import com.yash.tenantmanagement.domain.Image;
import com.yash.tenantmanagement.domain.Property;
import com.yash.tenantmanagement.dto.PropertyDto;

import jakarta.el.PropertyNotFoundException;

@Service
public class PropertyServiceImpl implements PropertyServiceInt {
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private ImageServiceInt imageService;

	@Override
	public PropertyDto saveProperty(PropertyDto propertyDto, MultipartFile file) throws IOException {
		Property property = new Property();
		property.setPropertyId(propertyDto.getPropertyId());
		property.setAddress(propertyDto.getAddress());
		property.setDescription(propertyDto.getDescription());
		property.setOwnerName(propertyDto.getOwnerName());
		property.setPrice(propertyDto.getPrice());
		
		if(file!=null && !file.isEmpty()) {
			Image image = this.imageService.saveImage(file);
			property.setProperty_image(image);
		}
		
		Property savedProperty = this.propertyRepository.save(property);
		PropertyDto dto = new PropertyDto();
		dto.setPropertyId(savedProperty.getPropertyId());
		dto.setAddress(savedProperty.getAddress());
		dto.setDescription(savedProperty.getDescription());
		dto.setPrice(savedProperty.getPrice());
		dto.setOwnerName(savedProperty.getOwnerName());
		
		return dto;
		
	}

	@Override
	public PropertyDto getProperty(Long propertyId) {
		Property property = this.propertyRepository.findById(propertyId)
		.orElseThrow(()->new PropertyNotFoundException("Property not found at this id:"+propertyId));
		
		PropertyDto dto = new PropertyDto();
		dto.setPropertyId(property.getPropertyId());
		dto.setAddress(property.getAddress());
		dto.setDescription(property.getDescription());
		dto.setOwnerName(property.getOwnerName());
		dto.setPrice(property.getPrice());
		return dto;
	}

	@Override
	public Property getImage(Long propertyId) {
		Property property = this.propertyRepository.findById(propertyId)
				.orElseThrow(()->new PropertyNotFoundException("Property not found at this id:"+propertyId));
	   return property;
	}

	@Override
	public List<PropertyDto> getAllProperties() {
		return this.propertyRepository.findAll().stream()
				.map(PropertyDto::new).toList();
	}

	@Override
	public PropertyDto updateProperty(PropertyDto propertyDto,Long propertyId) {
		Property property = this.propertyRepository.findById(propertyId)
				.orElseThrow(()->new PropertyNotFoundException("Property not found at this id:"+propertyId));
		property.setAddress(propertyDto.getAddress());
		property.setDescription(propertyDto.getDescription());
        property.setPrice(propertyDto.getPrice());
        property.setOwnerName(propertyDto.getOwnerName());
        
        Property updatedProperty = this.propertyRepository.save(property);
        return new PropertyDto(updatedProperty);
	}

	@Override
	public void deleteProperty(Long propertyId) {
		Property property = this.propertyRepository.findById(propertyId)
				.orElseThrow(()->new PropertyNotFoundException("Property not found at this id:"+propertyId));
		
		this.propertyRepository.delete(property);	
	}

	@Override
	public List<PropertyDto> searchPropertiesByAddress(String address) {
		return propertyRepository.findByAddress(address).stream()
				.map(PropertyDto::new).collect(Collectors.toList());
	}

	
}

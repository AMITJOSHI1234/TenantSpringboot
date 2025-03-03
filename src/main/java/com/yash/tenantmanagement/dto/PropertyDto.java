package com.yash.tenantmanagement.dto;

import com.yash.tenantmanagement.domain.Property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {

	private Long propertyId;
	
	@NotBlank(message = "Address is required!!!")
	private String address;
	
	@NotBlank(message = "Description is required!!!")
	private String description;
	
	@NotNull(message = "Price is required!!")
	private Long price;
	
	@NotBlank(message = "Owner name is required!!!")
	private String ownerName;
	
	public PropertyDto(Property property) {
		this.propertyId = property.getPropertyId();
		this.address = property.getAddress();
		this.description = property.getDescription();
		this.ownerName = property.getOwnerName();
		this.price = property.getPrice();
	}
	
}

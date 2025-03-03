package com.yash.tenantmanagement.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.yash.tenantmanagement.domain.Image;

public interface ImageServiceInt {

	Image saveImage(MultipartFile file) throws IOException;
	
	byte[] getImage(Long imageId);
}

package com.yash.tenantmanagement.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.tenantmanagement.dao.ImageRepository;
import com.yash.tenantmanagement.domain.Image;

@Service
public class ImageServiceImpl implements ImageServiceInt{

	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public Image saveImage(MultipartFile file) throws IOException {
		Image image = new Image();
		image.setData(file.getBytes());
		return imageRepository.save(image);
	}

	@Override
	public byte[] getImage(Long imageId) {
		Image image = this.imageRepository.findById(imageId)
		.orElseThrow(()->new RuntimeException("Image not found"));
		return image.getData();
	}

}

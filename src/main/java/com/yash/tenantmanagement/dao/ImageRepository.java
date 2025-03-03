package com.yash.tenantmanagement.dao;
/**
* This is a Repository for image
* @author amit joshi
*/
import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.tenantmanagement.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}

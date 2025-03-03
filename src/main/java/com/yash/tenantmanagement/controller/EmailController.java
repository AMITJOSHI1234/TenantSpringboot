package com.yash.tenantmanagement.controller;
/**
* This is a Email controller containing login and logout functionality
* @author amit joshi
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.tenantmanagement.dto.UserDto;
import com.yash.tenantmanagement.service.SendEmailService;
import com.yash.tenantmanagement.service.UserServiceInt;

@RestController
@RequestMapping("Email")
//@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {

	@Autowired
	private SendEmailService emailService;
	
	@Autowired
	private UserServiceInt userService;
	
	/*This is use for sending mail*/
	@GetMapping("sendEmail/{userId}")
	public ResponseEntity<UserDto> sendEmail(@PathVariable Long userId){
		UserDto userDto = this.userService.getUserById(userId);
		sendEmail(userDto.getEmail(),"Confirmation","Hi "+userDto.getUsername()+" Thankyou for selecting us now admin will contact you!!!");
		sendEmailToAdmin("ContactDetails","Hi Admin Please one user is intrested in property please contact "+userDto.getUsername()+" on"+userDto.getPhone()+" ThankYou...");
		return ResponseEntity.ok(userDto);
	}
	
	private void sendEmailToAdmin(String subject, String body) {
		this.emailService.sendMailToAdmin(body, subject);
	}

	public void sendEmail(String to,String subject,String body) {
		this.emailService.sendEmailToUser(to, subject, body);
	}
}

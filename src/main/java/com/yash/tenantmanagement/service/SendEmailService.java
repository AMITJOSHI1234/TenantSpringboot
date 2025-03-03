package com.yash.tenantmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("$(spring.mail.username)")
	private String fromEmailId;
	
	public void sendEmailToUser(String to,String body,String subject) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		
		this.javaMailSender.send(simpleMailMessage);
	}
	
	
	public void sendMailToAdmin(String body,String subject) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("");
        simpleMailMessage.setTo("");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        
        this.javaMailSender.send(simpleMailMessage);
	}
	
	
}

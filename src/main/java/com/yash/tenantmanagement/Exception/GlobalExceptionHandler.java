package com.yash.tenantmanagement.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.ConstraintViolationException;

@CrossOrigin(origins = "*")
@RestControllerAdvice
public class GlobalExceptionHandler {

	//Handle BindingResult error
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleBindingResultError(MethodArgumentNotValidException ex){
		BindingResult bindingResult = ex.getBindingResult();
		Map<String, String> error = new HashMap<>();
		for(FieldError errors:bindingResult.getFieldErrors()) {
			error.put(errors.getField(), errors.getDefaultMessage());
		}
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	
	//Handle userNotFoundException
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
		return new ResponseEntity<>("UserNotFound Exception "+ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//Handle propertyNotFoundException
	@ExceptionHandler(PropertyNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException(PropertyNotFoundException ex){
		return new ResponseEntity<>("PropertyNotFound Exception "+ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//Handle other Exceptions
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleExceptions(RuntimeException ex){
		return new ResponseEntity<String>("Exception occurs in your application "+ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//AuthJwt Exception
	@ExceptionHandler(AuthException.class)
	public ResponseEntity<String> unAuthorizedUser(AuthException ex){
		return new ResponseEntity<>("Auth Exception "+ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
}

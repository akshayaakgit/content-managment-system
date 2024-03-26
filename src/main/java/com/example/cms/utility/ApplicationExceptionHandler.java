package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.exception.UserAlreadyExistExcepption;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler {
	private ErrorStructure<String> structure;

	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String message, String rootCause) {
		return new ResponseEntity<ErrorStructure<String>>(
				structure.setMessage(message).setStatuscode(status.value()).setRootCause(rootCause), status);

	}

	@ExceptionHandler
	private ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistByEmail(UserAlreadyExistExcepption ex) {
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "User Already Exist with the given emailId");
	}

}

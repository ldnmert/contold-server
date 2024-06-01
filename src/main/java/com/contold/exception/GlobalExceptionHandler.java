package com.contold.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<Object> handleUnauthorizedAccessException(UnauthorizedAccessException ex, WebRequest request) {
		System.out.println("gasdsadsa");
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

}

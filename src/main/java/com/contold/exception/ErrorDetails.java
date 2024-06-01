package com.contold.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

	public ErrorDetails(int status, String error, String message, String path) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

}

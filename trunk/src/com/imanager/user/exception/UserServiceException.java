package com.imanager.user.exception;

@SuppressWarnings("serial")
public class UserServiceException extends RuntimeException {

	public UserServiceException() {
		super();
	}

	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(Throwable cause) {
		super(cause);
	}
	
}

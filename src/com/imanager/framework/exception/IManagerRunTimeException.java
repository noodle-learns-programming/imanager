package com.imanager.framework.exception;

@SuppressWarnings("serial")
public class IManagerRunTimeException extends RuntimeException{

	public IManagerRunTimeException() {
		super();
	}

	public IManagerRunTimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public IManagerRunTimeException(String message) {
		super(message);
	}

	public IManagerRunTimeException(Throwable cause) {
		super(cause);
	}
}

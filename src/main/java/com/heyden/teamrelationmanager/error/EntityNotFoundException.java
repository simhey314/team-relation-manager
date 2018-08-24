package com.heyden.teamrelationmanager.error;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 205795182198324140L;

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}
}

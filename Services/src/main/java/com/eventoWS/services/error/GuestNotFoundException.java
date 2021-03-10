package com.eventoWS.services.error;

public class GuestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 42095237612050800L;
	
	public GuestNotFoundException() {
	}

	public GuestNotFoundException(String message) {
		super(message);
	}

	public GuestNotFoundException(Throwable cause) {
		super(cause);
	}

	public GuestNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public GuestNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

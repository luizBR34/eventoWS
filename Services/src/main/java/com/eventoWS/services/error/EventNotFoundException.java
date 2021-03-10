package com.eventoWS.services.error;

public class EventNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3367033150771091336L;
	
	
	public EventNotFoundException() {
	}

	public EventNotFoundException(String message) {
		super(message);
	}

	public EventNotFoundException(Throwable cause) {
		super(cause);
	}

	public EventNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

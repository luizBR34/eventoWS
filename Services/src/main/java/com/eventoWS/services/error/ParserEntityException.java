package com.eventoWS.services.error;

public class ParserEntityException extends RuntimeException {

	private static final long serialVersionUID = -3367033150771091336L;
	private int errorOffset;
	
	
	public ParserEntityException() {
	}

	public ParserEntityException(String message) {
		super(message);
	}

	public ParserEntityException(Throwable cause) {
		super(cause);
	}
	

    public ParserEntityException(String s, int errorOffset) {
        super(s);
        this.errorOffset = errorOffset;
    }
	
	public ParserEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParserEntityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	
    public int getErrorOffset () {
        return errorOffset;
    }

}

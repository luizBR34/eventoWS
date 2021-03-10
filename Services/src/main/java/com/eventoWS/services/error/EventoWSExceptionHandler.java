package com.eventoWS.services.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventoWSExceptionHandler {
	
	
	@ExceptionHandler
	public ResponseEntity<EventoWSErrorResponse> handleEventNotFoundException(EventNotFoundException exc) {
		
		EventoWSErrorResponse error = new EventoWSErrorResponse(HttpStatus.NOT_FOUND.value(), 
																exc.getMessage(), 
																System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<EventoWSErrorResponse> handleGuestNotFoundException(GuestNotFoundException exc) {
		
		EventoWSErrorResponse error = new EventoWSErrorResponse(HttpStatus.NOT_FOUND.value(), 
																exc.getMessage(), 
																System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<EventoWSErrorResponse> handleParseException(ParserEntityException exc) {
		
		EventoWSErrorResponse error = new EventoWSErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
																exc.getMessage(), 
																System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
	@ExceptionHandler
	public ResponseEntity<EventoWSErrorResponse> handleException(Exception exc) {
		
		EventoWSErrorResponse error = new EventoWSErrorResponse(HttpStatus.BAD_REQUEST.value(), 
																exc.getMessage(),
																System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}

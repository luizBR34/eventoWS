package com.eventoWS.services.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoWSErrorResponse {
	
	private int status;
	private String message;
	private long timeStamp;
}

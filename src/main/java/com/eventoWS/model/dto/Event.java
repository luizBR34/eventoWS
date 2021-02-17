package com.eventoWS.model.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event implements Serializable {
	
	private static final long serialVersionUID = 4965925525811003013L;

	private long code;

	private String name;

	private String city;

	private Date date;

	private Time time;

	private List<Guest> guests;
}

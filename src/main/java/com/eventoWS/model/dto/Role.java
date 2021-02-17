package com.eventoWS.model.dto;

import java.io.Serializable;
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
public class Role implements Serializable {

	private static final long serialVersionUID = -6878435239875390450L;

	private Long id;

	private String name;
	
	private List<User> users;

}

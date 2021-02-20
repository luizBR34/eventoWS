package com.eventoWS.mappers;

import java.util.stream.Collectors;

import com.eventoWS.models.EventEntity;
import com.eventoWS.models.GuestEntity;
import com.eventoWS.models.RoleEntity;
import com.eventoWS.models.UserEntity;
import com.eventoWS.models.dto.Event;
import com.eventoWS.models.dto.Guest;
import com.eventoWS.models.dto.Role;
import com.eventoWS.models.dto.User;

public class Converter {

	public static <T> Object convertEntityToDTO(T t) {

		if (t instanceof EventEntity) {

			return Event.builder()
					.code(((EventEntity) t).getCode())
					.name(((EventEntity) t).getName())
					.city(((EventEntity) t).getCity())
					.date(((EventEntity) t).getDate())
					.time(((EventEntity) t).getTime()).build();

		} else if (t instanceof GuestEntity) {

			return Guest.builder()
					.id(((GuestEntity) t).getId())
					.guestName(((GuestEntity) t).getGuestName())
					.event((Event) convertEntityToDTO(((GuestEntity) t).getEvent())).build();

		} else if (t instanceof RoleEntity) {

			return Role.builder()
					.id(((RoleEntity) t).getId())
					.name(((RoleEntity) t).getName()).build();

		} else if (t instanceof UserEntity) {

			return User.builder()
					.id(((UserEntity) t).getId())
					.userName(((UserEntity) t).getUserName())
					.password(((UserEntity) t).getPassword())
					.firstName(((UserEntity) t).getFirstName())
					.lastName(((UserEntity) t).getLastName())
					.email(((UserEntity) t).getEmail())
					.roles(((UserEntity) t).getRoles()
							.stream()
							.map(r -> (Role) convertEntityToDTO(r))
							.collect(Collectors.toList()))
					.build();

		} else { }

		return null;
	}

	public static <T> Object convertDTOToEntity(T t) {

		if (t instanceof Event) {

			return EventEntity.builder()
					.code(((Event) t).getCode())
					.name(((Event) t).getName())
					.city(((Event) t).getCity())
					.date(((Event) t).getDate())
					.time(((Event) t).getTime()).build();

		} else if (t instanceof Guest) {

			return GuestEntity.builder()
					.id(((Guest) t).getId())
					.guestName(((Guest) t).getGuestName())
					.event((EventEntity) convertDTOToEntity(((Guest) t).getEvent())).build();

		} else { }

		return null;
	}

}

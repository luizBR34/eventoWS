package com.eventoWS.mappers;

import java.text.ParseException;
import java.util.stream.Collectors;

import com.eventoApp.mappers.utils.DateUtils;
import com.eventoApp.models.Event;
import com.eventoApp.models.Guest;
import com.eventoApp.models.Role;
import com.eventoApp.models.User;
import com.eventoWS.persistence.entity.EventEntity;
import com.eventoWS.persistence.entity.GuestEntity;
import com.eventoWS.persistence.entity.RoleEntity;
import com.eventoWS.persistence.entity.UserEntity;

public class Converter {

	public static <T> Object convertEntityToDTO(T t) {

		if (t instanceof EventEntity) {

			return Event.builder()
					.code(((EventEntity) t).getCode())
					.name(((EventEntity) t).getName())
					.city(((EventEntity) t).getCity())
					.date(DateUtils.formatDate(((EventEntity) t).getDate()))
					.time(DateUtils.formatDate(((EventEntity) t).getTime())).build();

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

	public static <T> Object convertDTOToEntity(T t) throws ParseException {

		if (t instanceof Event) {

			return EventEntity.builder()
					.code(((Event) t).getCode())
					.name(((Event) t).getName())
					.city(((Event) t).getCity())
					.date(DateUtils.parseDate(((Event) t).getDate()))
					.time(DateUtils.parseDate(((Event) t).getTime())).build();

		} else if (t instanceof Guest) {

			return GuestEntity.builder()
					.id(((Guest) t).getId())
					.guestName(((Guest) t).getGuestName())
					.event((EventEntity) convertDTOToEntity(((Guest) t).getEvent())).build();

		} else { }

		return null;
	}

}

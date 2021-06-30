package com.eventoWS.services.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventoApp.models.Event;
import com.eventoApp.models.Guest;
import com.eventoApp.models.User;
import com.eventoWS.mappers.Converter;
import com.eventoWS.persistence.entity.EventEntity;
import com.eventoWS.persistence.entity.UserEntity;
import com.eventoWS.persistence.repository.EventRepository;
import com.eventoWS.persistence.repository.GuestRepository;
import com.eventoWS.persistence.repository.UserRepository;
import com.eventoWS.services.ClientService;
import com.eventoWS.services.error.EventNotFoundException;
import com.eventoWS.services.error.GuestNotFoundException;
import com.eventoWS.services.error.ParserEntityException;

@Service
public class DefaultClientService implements ClientService {

	@Autowired
	EventRepository er;

	@Autowired
	GuestRepository gr;

	@Autowired
	UserRepository ur;

	@Override
	public List<Event> eventList(String username) {

		List<Event> eventList = null;
		UserEntity user = ur.findByUserName(username);

		if (nonNull(user)) {

			List<EventEntity> eventEntityList = er.findAllByUser(user);

			if (isNull(eventEntityList)) {
				eventEntityList = new ArrayList<com.eventoWS.persistence.entity.EventEntity>();
			}

			eventList = (eventEntityList.stream().findAny().isPresent())
					? eventEntityList.stream().map(e -> (Event) convertEntityToDTO(e)).collect(Collectors.toList())
					: new ArrayList<Event>();

		} else {
			eventList = Collections.emptyList();
		}

		return eventList;
	}

	@Override
	public Event seekEvent(long code) {

		Event event = null;

		try {
			event = (Event) Converter.convertEntityToDTO(er.findByCode(code));
		} catch (ParseException e) {
			throw new ParserEntityException(e.getMessage(), e.getErrorOffset());
		}

		if (isNull(event)) {
			throw new EventNotFoundException("Event could not be found: " + code);
		}

		return event;
	}

	@Override
	public Guest seekGuest(long id) {

		Guest guest = null;

		try {
			guest = (Guest) Converter.convertEntityToDTO(gr.findById(id));
		} catch (ParseException e) {
			throw new ParserEntityException(e.getMessage(), e.getErrorOffset());
		}

		if (isNull(guest)) {
			throw new GuestNotFoundException("Guest could not be found: " + id);
		}

		return guest;
	}

	@Override
	public User seekUser(String login) {

		User user;

		try {
			user = (User) Converter.convertEntityToDTO(ur.findByUserName(login));
		} catch (ParseException e) {
			throw new ParserEntityException(e.getMessage(), e.getErrorOffset());
		}

		return user;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Guest> guestList(long eventCode) {

		com.eventoWS.persistence.entity.EventEntity event = er.findByCode(eventCode);
		Iterable<com.eventoWS.persistence.entity.GuestEntity> iterableList = gr.findByEvent(event);

		List<com.eventoWS.persistence.entity.GuestEntity> guestList = new ArrayList<>();
		iterableList.forEach(guestList::add);

		return guestList.stream().map(g -> (Guest) convertEntityToDTO(g)).collect(Collectors.toList());
	}

	@Override
	public void saveGuest(long eventCode, Guest guest) {

		com.eventoWS.persistence.entity.EventEntity event = er.findByCode(eventCode);
		com.eventoWS.persistence.entity.GuestEntity guestEntity;
		try {
			guestEntity = ((com.eventoWS.persistence.entity.GuestEntity) Converter.convertDTOToEntity(guest));
		} catch (ParseException e) {
			throw new ParserEntityException(e.getMessage(), e.getErrorOffset());
		}
		guestEntity.setEvent(event);
		gr.save(guestEntity);
	}

	@Override
	public void saveEvent(Event evento) {

		com.eventoWS.persistence.entity.EventEntity event;
		try {
			event = (com.eventoWS.persistence.entity.EventEntity) Converter.convertDTOToEntity(evento);
		} catch (ParseException e) {
			throw new ParserEntityException(e.getMessage(), e.getErrorOffset());
		}
		er.save(event);
	}

	@Override
	public void deleteEvent(long code) {
		com.eventoWS.persistence.entity.EventEntity event = er.findByCode(code);
		er.delete(event);
	}

	@Override
	public Event deleteGuest(long id) {

		com.eventoWS.persistence.entity.GuestEntity guest = gr.findById(id);
		gr.delete(guest);

		try {
			return (Event) Converter.convertEntityToDTO(guest.getEvent());
		} catch (ParseException e) {
			throw new ParserEntityException(e.getMessage(), e.getErrorOffset());
		}
	}

	private <T> Object convertEntityToDTO(T t) {

		try {
			return Converter.convertEntityToDTO(t);
		} catch (ParseException e) {
			throw new ParserEntityException(e.getMessage(), e.getErrorOffset());
		}
	}
}

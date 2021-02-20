package com.eventoWS.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventoWS.mappers.Converter;
import com.eventoWS.models.GuestEntity;
import com.eventoWS.models.dto.Event;
import com.eventoWS.models.dto.Guest;
import com.eventoWS.models.dto.User;
import com.eventoWS.persistence.EventRepository;
import com.eventoWS.persistence.GuestRepository;
import com.eventoWS.persistence.UserRepository;
import com.eventoWS.services.ClientService;

@Service
public class DefaultClientService implements ClientService {
	
	
	@Autowired
	EventRepository er;
	
	@Autowired
	GuestRepository gr;
	
	@Autowired
	UserRepository ur;
	

	@Override
	public List<Event> eventList() {
		
		Iterable<com.eventoWS.models.EventEntity> events = er.findAll();

		List<com.eventoWS.models.EventEntity> eventList = new ArrayList<>();
		events.forEach(eventList::add);

		return eventList.stream()
					    .map(e -> (Event) Converter.convertEntityToDTO(e))
					    .collect(Collectors.toList());
	}

	@Override
	public Event seekEvent(long code) {
		
		Event event = (Event) Converter.convertEntityToDTO(er.findByCode(code));	
		return event;
	}
	
	
	@Override
	public Guest seekGuest(long id) {
		
		GuestEntity guestEntity = gr.findById(id);
		return (Guest) Converter.convertEntityToDTO(guestEntity);
	}
	
	

	@Override
	public User seekUser(String login) {
		
		User user = (User) Converter.convertEntityToDTO(ur.findByUserName(login));
		return user;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

	}


	@Override
	public List<Guest> guestList(long eventCode) {
		
		com.eventoWS.models.EventEntity event = er.findByCode(eventCode);
		Iterable<com.eventoWS.models.GuestEntity> iterableList = gr.findByEvent(event);
		
		List<com.eventoWS.models.GuestEntity> guestList = new ArrayList<>();
		iterableList.forEach(guestList::add);
		
		return guestList.stream()
				 .map(g -> (Guest) Converter.convertEntityToDTO(g))
				 .collect(Collectors.toList());
	}

	@Override
	public void saveGuest(long eventCode, Guest guest) {
		
		com.eventoWS.models.EventEntity event = er.findByCode(eventCode);
		com.eventoWS.models.GuestEntity guestEntity = ((com.eventoWS.models.GuestEntity) Converter.convertDTOToEntity(guest));
		guestEntity.setEvent(event);
		gr.save(guestEntity);
	}

	@Override
	public void saveEvent(Event evento) {
		
		com.eventoWS.models.EventEntity event = (com.eventoWS.models.EventEntity) Converter.convertDTOToEntity(evento);
		er.save(event);
	}

	@Override
	public void deleteEvent(long code) {
		com.eventoWS.models.EventEntity event = er.findByCode(code);
		er.delete(event);
	}

	@Override
	public Event deleteGuest(long id) {
		
		com.eventoWS.models.GuestEntity guest = gr.findById(id);
		gr.delete(guest);
		
		com.eventoWS.models.EventEntity event = guest.getEvent();
		return (Event) Converter.convertEntityToDTO(event);
	}

}

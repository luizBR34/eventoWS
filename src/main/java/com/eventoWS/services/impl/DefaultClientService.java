package com.eventoWS.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventoWS.mappers.Converter;
import com.eventoWS.model.GuestEntity;
import com.eventoWS.model.dto.Event;
import com.eventoWS.model.dto.Guest;
import com.eventoWS.model.dto.User;
import com.eventoWS.repository.EventRepository;
import com.eventoWS.repository.GuestRepository;
import com.eventoWS.repository.UserRepository;
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
		
		Iterable<com.eventoWS.model.EventEntity> events = er.findAll();

		List<com.eventoWS.model.EventEntity> eventList = new ArrayList<>();
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
		
		com.eventoWS.model.EventEntity event = er.findByCode(eventCode);
		Iterable<com.eventoWS.model.GuestEntity> iterableList = gr.findByEvent(event);
		
		List<com.eventoWS.model.GuestEntity> guestList = new ArrayList<>();
		iterableList.forEach(guestList::add);
		
		return guestList.stream()
				 .map(g -> (Guest) Converter.convertEntityToDTO(g))
				 .collect(Collectors.toList());
	}

	@Override
	public void saveGuest(long eventCode, Guest guest) {
		
		com.eventoWS.model.EventEntity event = er.findByCode(eventCode);
		com.eventoWS.model.GuestEntity guestEntity = ((com.eventoWS.model.GuestEntity) Converter.convertDTOToEntity(guest));
		guestEntity.setEvent(event);
		gr.save(guestEntity);
	}

	@Override
	public void saveEvent(Event evento) {
		
		com.eventoWS.model.EventEntity event = (com.eventoWS.model.EventEntity) Converter.convertDTOToEntity(evento);
		er.save(event);
	}

	@Override
	public void deleteEvent(long code) {
		com.eventoWS.model.EventEntity event = er.findByCode(code);
		er.delete(event);
	}

	@Override
	public Event deleteGuest(long id) {
		
		com.eventoWS.model.GuestEntity guest = gr.findById(id);
		gr.delete(guest);
		
		com.eventoWS.model.EventEntity event = guest.getEvent();
		return (Event) Converter.convertEntityToDTO(event);
	}

}

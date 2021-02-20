package com.eventoWS.services;

import java.util.List;

import com.eventoWS.models.dto.Event;
import com.eventoWS.models.dto.Guest;
import com.eventoWS.models.dto.User;

public interface ClientService {
	
	public List<Event> eventList();
	
	public Event seekEvent(long code);
	
	public Guest seekGuest(long id);

	public User seekUser(String login);
	
	public void saveUser(User user);
	
	public List<Guest> guestList(long eventCode);
	
	public void saveGuest(long eventCode, Guest guest);
	
	public void saveEvent(Event evento);
	
	public void deleteEvent(long code);
	
	public Event deleteGuest(long id);
}

package com.eventoWS.services;

import java.util.List;

import com.eventoApp.models.Event;
import com.eventoApp.models.Guest;
import com.eventoApp.models.User;

public interface ClientService {
	
	public List<Event> eventList(String username);
	
	public Event seekEvent(long code);

	public Event seekLastEventSaved();
	
	public Guest seekGuest(long id);

	public User seekUser(String login);
	
	public void saveUser(User user);
	
	public List<Guest> guestList(long eventCode);
	
	public void saveGuest(long eventCode, Guest guest);
	
	public void saveEvent(Event evento);
	
	public void deleteEvent(long code);
	
	public Event deleteGuest(long id);
}

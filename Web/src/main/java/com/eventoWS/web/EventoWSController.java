package com.eventoWS.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventoApp.models.Event;
import com.eventoApp.models.Guest;
import com.eventoApp.models.User;
import com.eventoWS.services.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST do eventoWS")
@RestController
@RequestMapping("/eventoWS")
public class EventoWSController {
	
	@Autowired
	private ClientService service;

	@ApiOperation(value="Returns the list of all events scheduled for a specific user.")
	@GetMapping(value="/eventList/{username}", produces="application/json")
	public @ResponseBody List<Event> eventList(@PathVariable("username") String username) {
		
		return service.eventList(username);
	}
	
	
	@ApiOperation(value="Get an event by its code.")
	@GetMapping(value="/seekEvent/{code}", produces="application/json")
	public @ResponseBody Event seekEvent(@PathVariable("code") long code) {
		
		return service.seekEvent(code);
	}


	@ApiOperation(value="Get the last event saved into database.")
	@GetMapping(value="/seekLastEventSaved", produces="application/json")
	public @ResponseBody Event seekLastEventSaved() {

		return service.seekLastEventSaved();
	}
	
	
	@ApiOperation(value="Get a guest by its code.")
	@GetMapping(value="/seekGuest/{id}", produces="application/json")
	public @ResponseBody Guest seekGuest(@PathVariable("id") long id) {
		
		return service.seekGuest(id);
	}
	
	
	@ApiOperation(value="Get an User by its username.")
	@GetMapping(value="/seekUser/{login}", produces="application/json")
	public @ResponseBody User seekUser(@PathVariable("login") String login) {

		return service.seekUser(login);
	}
	
	
	@ApiOperation(value="Returns the guest list of a specific event.")
	@GetMapping(value="/guestList/{eventCode}", produces="application/json")
	public @ResponseBody List<Guest> guestList(@PathVariable("eventCode") long eventCode) {

		return service.guestList(eventCode);
	}
	
	
	@ApiOperation(value="Sign up a guest from event code.")
	@PostMapping("/saveGuest/{eventCode}")
	public void saveGuest(@PathVariable("eventCode") long eventCode, @RequestBody @Valid Guest guest) {
		
		service.saveGuest(eventCode, guest);
	}
	
	
	//Metodo de cadastramento de evento via POST
	//@RequestBody indica que Evento virá no corpo da Requisição
	@ApiOperation(value="Sign up an event.")
	@PostMapping("/saveEvent")
	public void saveEvent(@RequestBody @Valid Event event) {

		service.saveEvent(event);
	}
	
	
	@ApiOperation(value="Delete an event from its code.")
	@DeleteMapping("/deleteEvent/{code}")
	public void deleteEvent(@PathVariable("code") long code) {
		
		service.deleteEvent(code);
	}
	

	@ApiOperation(value="Delete a guest from its id.")
	@DeleteMapping(value="/deleteGuest/{id}", produces="application/json")
	public @ResponseBody Event deleteGuest(@PathVariable("id") long id) {

		return service.deleteGuest(id);
	}
}

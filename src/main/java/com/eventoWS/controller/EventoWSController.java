package com.eventoWS.controller;

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

import com.eventoWS.model.User;
import com.eventoWS.model.Guest;
import com.eventoWS.model.Event;
import com.eventoWS.repository.UserRepository;
import com.eventoWS.repository.GuestRepository;
import com.eventoWS.repository.EventRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST do eventoWS")
@RestController
@RequestMapping("/eventoWS")
public class EventoWSController {
	
	@Autowired
	private EventRepository er;
	
	@Autowired
	private GuestRepository gr;
	
	@Autowired
	private UserRepository ur;
	
	//Define método Get de request e Json de resposta
	//@ResponseBody indica que a resposta virá no corpo.
	@ApiOperation(value="Returns the list of all events scheduled.")
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Event> eventList() {
		Iterable<Event> eventList = er.findAll();
		return eventList;
	}
	
	
	@ApiOperation(value="Get an event by its code.")
	@GetMapping(value="/seekEvent/{code}", produces="application/json")
	public @ResponseBody Event seekEvent(@PathVariable("code") long code) {
		Event event = er.findByCode(code);		
		return event;
	}
	
	
	@ApiOperation(value="Get a guest by its code.")
	@GetMapping(value="/seekGuest/{id}", produces="application/json")
	public @ResponseBody Guest seekGuest(@PathVariable("id") long id) {
		Guest guest = gr.findById(id);
		return guest;
	}
	
	
	@ApiOperation(value="Get an User by its username.")
	@GetMapping(value="/seekUser/{login}", produces="application/json")
	public @ResponseBody User seekUser(@PathVariable("login") String login) {
		User usuario = ur.findByUserName(login);
		return usuario;
	}
	
	
	@ApiOperation(value="Returns the guest list of a specific event.")
	@GetMapping(value="/guestList/{eventCode}", produces="application/json")
	public @ResponseBody List<Guest> guestList(@PathVariable("eventCode") long eventCode) {
		Event event = er.findByCode(eventCode);
		List<Guest> guestList = (List<Guest>) gr.findByEvent(event);
		return guestList;
	}
	
	
	@ApiOperation(value="Sign up a guest from event code.")
	@PostMapping("/saveGuest/{eventCode}")
	public void saveGuest(@PathVariable("eventCode") long eventCode, @RequestBody @Valid Guest guest) {
		Event event = er.findByCode(eventCode);
		guest.setEvent(event);
		gr.save(guest);
	}
	
	
	//Metodo de cadastramento de evento via POST
	//@RequestBody indica que Evento virá no corpo da Requisição
	@ApiOperation(value="Sign up an event.")
	@PostMapping("/saveEvent")
	public void saveEvent(@RequestBody @Valid Event event) {
		er.save(event);
	}
	
	
	@ApiOperation(value="Delete an event from its code.")
	@DeleteMapping("/deleteEvent/{code}")
	public void deleteEvent(@PathVariable("code") long code) {
		Event event = er.findByCode(code);
		er.delete(event);
	}
	

	@ApiOperation(value="Delete a guest from its id.")
	@DeleteMapping(value="/deleteGuest/{id}", produces="application/json")
	public @ResponseBody Event deleteGuest(@PathVariable("id") long id) {
		Guest guest = gr.findById(id);
		gr.delete(guest);
		
		Event event = guest.getEvent();
		return event;
	}
}

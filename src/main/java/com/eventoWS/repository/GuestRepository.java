package com.eventoWS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.model.Guest;
import com.eventoWS.model.Event;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
	
	Iterable<Guest> findByEvent(Event event);
	Guest findById(long id);
}

package com.eventoWS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
	
	Event findByCode(long code);
	
}

package com.eventoWS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.model.EventEntity;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Long> {
	
	EventEntity findByCode(long code);
	
}

package com.eventoWS.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.models.EventEntity;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Long> {
	
	EventEntity findByCode(long code);
	
}

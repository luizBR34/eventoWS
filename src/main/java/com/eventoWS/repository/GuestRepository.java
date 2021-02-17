package com.eventoWS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.model.GuestEntity;
import com.eventoWS.model.EventEntity;

@Repository
public interface GuestRepository extends CrudRepository<GuestEntity, Long> {
	
	Iterable<GuestEntity> findByEvent(EventEntity event);
	GuestEntity findById(long id);
}

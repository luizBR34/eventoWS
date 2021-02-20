package com.eventoWS.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.models.GuestEntity;
import com.eventoWS.models.EventEntity;

@Repository
public interface GuestRepository extends CrudRepository<GuestEntity, Long> {
	
	Iterable<GuestEntity> findByEvent(EventEntity event);
	GuestEntity findById(long id);
}

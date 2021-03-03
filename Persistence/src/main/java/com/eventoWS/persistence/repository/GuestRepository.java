package com.eventoWS.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.persistence.entity.EventEntity;
import com.eventoWS.persistence.entity.GuestEntity;

@Repository
public interface GuestRepository extends CrudRepository<GuestEntity, Long> {
	
	Iterable<GuestEntity> findByEvent(EventEntity event);
	GuestEntity findById(long id);
}

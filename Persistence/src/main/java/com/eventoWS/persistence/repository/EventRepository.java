package com.eventoWS.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.persistence.entity.EventEntity;
import com.eventoWS.persistence.entity.UserEntity;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Long> {
	
	EventEntity findByCode(long code);
	List<EventEntity> findAllByUser(UserEntity user);
	Optional<EventEntity> findTopByOrderByCodeDesc();
}

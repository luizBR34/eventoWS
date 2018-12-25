package com.eventoWS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventoWS.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, String> {
	
	
}

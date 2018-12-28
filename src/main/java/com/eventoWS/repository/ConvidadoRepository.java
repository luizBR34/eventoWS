package com.eventoWS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.model.Convidado;
import com.eventoWS.model.Evento;

@Repository
public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
	
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByRg(String rg);
}

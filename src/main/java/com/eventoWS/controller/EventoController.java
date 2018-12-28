package com.eventoWS.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventoWS.model.Convidado;
import com.eventoWS.model.Evento;
import com.eventoWS.repository.ConvidadoRepository;
import com.eventoWS.repository.EventoRepository;

@RestController
@RequestMapping("/eventoWS")
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	//Define método Get de request e Json de resposta
	//@ResponseBody indica que a resposta virá no corpo.
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Evento> listaEventos() {
		Iterable<Evento> listaEventos = er.findAll();
		return listaEventos;
	}
	
	
	@GetMapping(value="/buscaEvento/{codigo}", produces="application/json")
	public @ResponseBody Evento buscaEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);		
		return evento;
	}
	
	
	@GetMapping(value="/buscaConvidado/{rg}", produces="application/json")
	public @ResponseBody Convidado buscaConvidado(@PathVariable("rg") String rg) {
		Convidado convidado = cr.findByRg(rg);	
		return convidado;
	}
	
	

	@PostMapping(produces="application/json")
	public @ResponseBody Iterable<Convidado> listaConvidados(@RequestBody @Valid Evento evento) {		
		Iterable<Convidado> listaConvidados = cr.findByEvento(evento);
		return listaConvidados;
	}
	
	
	
	@PostMapping("/cadastraConvidado/{codigoEvento}")
	public void cadastraConvidado(@PathVariable("codigoEvento") long codigoEvento, @RequestBody @Valid Convidado convidado) {
		Evento evento = er.findByCodigo(codigoEvento);
		convidado.setEvento(evento);
		cr.save(convidado);
	}
	
	

	//Metodo de cadastramento de evento via POST
	//@RequestBody indica que Evento virá no corpo da Requisição
	@PostMapping("/cadastraEvento")
	public void cadastraEvento(@RequestBody @Valid Evento evento) {
		er.save(evento);
	}
	
	
	@DeleteMapping("/deletaEvento/{codigo}")
	public void deletaEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
	}
	

	@DeleteMapping("/deletaConvidado/{rg}")
	public void deletaConvidado(@PathVariable("rg") String rg) {
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);
	}
}

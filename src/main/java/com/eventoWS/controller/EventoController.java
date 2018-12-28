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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST do eventoWS")
@RestController
@RequestMapping("/eventoWS")
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	//Define método Get de request e Json de resposta
	//@ResponseBody indica que a resposta virá no corpo.
	@ApiOperation(value="Retorna a lista de todos os eventos")
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Evento> listaEventos() {
		Iterable<Evento> listaEventos = er.findAll();
		return listaEventos;
	}
	
	
	@ApiOperation(value="Retorna um evento pelo seu código")
	@GetMapping(value="/buscaEvento/{codigo}", produces="application/json")
	public @ResponseBody Evento buscaEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);		
		return evento;
	}
	
	
	@ApiOperation(value="Retorna um convidado por seu RG")
	@GetMapping(value="/buscaConvidado/{rg}", produces="application/json")
	public @ResponseBody Convidado buscaConvidado(@PathVariable("rg") String rg) {
		Convidado convidado = cr.findByRg(rg);	
		return convidado;
	}
	
	
	@ApiOperation(value="Retorna a lista de convidados de um determinado evento")
	@PostMapping(produces="application/json")
	public @ResponseBody Iterable<Convidado> listaConvidados(@RequestBody @Valid Evento evento) {		
		Iterable<Convidado> listaConvidados = cr.findByEvento(evento);
		return listaConvidados;
	}
	
	
	@ApiOperation(value="Cadastra um convidado a partir de um código de evento")
	@PostMapping("/cadastraConvidado/{codigoEvento}")
	public void cadastraConvidado(@PathVariable("codigoEvento") long codigoEvento, @RequestBody @Valid Convidado convidado) {
		Evento evento = er.findByCodigo(codigoEvento);
		convidado.setEvento(evento);
		cr.save(convidado);
	}
	
	
	//Metodo de cadastramento de evento via POST
	//@RequestBody indica que Evento virá no corpo da Requisição
	@ApiOperation(value="Cadastra um evento via POST")
	@PostMapping("/cadastraEvento")
	public void cadastraEvento(@RequestBody @Valid Evento evento) {
		er.save(evento);
	}
	
	
	@ApiOperation(value="Exclui um evento por seu código")
	@DeleteMapping("/deletaEvento/{codigo}")
	public void deletaEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
	}
	

	@ApiOperation(value="Exclui um convidado por seu RG")
	@DeleteMapping("/deletaConvidado/{rg}")
	public void deletaConvidado(@PathVariable("rg") String rg) {
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);
	}
}

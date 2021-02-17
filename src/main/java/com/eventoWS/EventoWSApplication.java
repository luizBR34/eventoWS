package com.eventoWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* PARA TESTAR ESSE WEB SERVICE REST
 	NO POSTMAN
 1) Listar os eventos cadastrados
 Informar a url localhost:8080/eventoWS
 
 2) Cadastrar evento
 Em headers, acrescentar KEY: Content-type / Value: application-json
 Em body montar o json conforme o arquivo Teste.json
 
 3) Para testar o método POST basta mudar o tipo de método
 
 4) Para testar o método DELETE, além de mudar o método, deve-se informar o código que deve ser apagado.
 
 */

// TESTAR API SWAGGER: http://localhost:9090/swagger-ui.html


@SpringBootApplication
public class EventoWSApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventoWSApplication.class, args);
	}
}

package com.eventoWS.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/* PARA TESTAR ESSE WEB SERVICE REST
 	NO POSTMAN
 1) Listar os eventos cadastrados
 Informar a url localhost:9090/eventoWS
 
 2) Cadastrar evento
 Em headers, acrescentar KEY: Content-type / Value: application-json
 Em body montar o json conforme o arquivo Teste.json
 
 3) Para testar o método POST basta mudar o tipo de método
 
 4) Para testar o método DELETE, além de mudar o método, deve-se informar o código que deve ser apagado.
 
 */

// TESTAR API SWAGGER: http://localhost:9090/swagger-ui.html


@SpringBootApplication()
@ComponentScan(basePackages = {"com.eventoWS"})
@EntityScan("com.eventoWS.persistence.entity")
@EnableJpaRepositories("com.eventoWS.persistence.repository")
@EnableSwagger2
public class EventoWSApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventoWSApplication.class, args);
	}
}

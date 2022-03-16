package com.eventoWS.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
	docker build -t luizpovoa/eventows:0.0.1-SNAPSHOT .
	docker run -p 9090:9090 -d -e DATABASE_HOST=172.18.0.4 -e DATABASE_PORT=3306 -e DATABASE_USERNAME=root -e DATABASE_PASSWORD=hadouken83 --network eventoapp-network --name EventoWS luizpovoa/eventows:0.0.1-SNAPSHOT
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

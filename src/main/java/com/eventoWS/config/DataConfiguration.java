package com.eventoWS.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;

@Configuration
@Profile("dev")
public class DataConfiguration {
	
	//Configura a conexão com o Banco
	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/eventoWS"); 
		dataSource.setUsername("root");
		dataSource.setPassword("hadouken83");
		return dataSource;
	}
	
	//Configura o Hibernate
	@Bean
	public JpaVendorAdapter jpaVenderAdapter() {

		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true); //Loga no console a query gerada pelo Hibernate
		adapter.setGenerateDdl(true); //Permite que o Hibernate crie as tabelas automaticamente
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		adapter.setPrepareConnection(true);
		return adapter;
	}
}

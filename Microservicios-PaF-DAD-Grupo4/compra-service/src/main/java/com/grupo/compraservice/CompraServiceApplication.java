package com.grupo.compraservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CompraServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompraServiceApplication.class, args);
	}

}

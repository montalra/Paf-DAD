package com.marketing.marcas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MarcasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcasApplication.class, args);
	}

}

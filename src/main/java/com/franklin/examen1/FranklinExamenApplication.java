package com.franklin.examen1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FranklinExamenApplication {

	public static void main(String[] args) {
		SpringApplication.run(FranklinExamenApplication.class, args);
	}

}

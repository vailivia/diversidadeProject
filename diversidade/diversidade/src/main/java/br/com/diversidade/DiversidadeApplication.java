package br.com.diversidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiversidadeApplication {

	public static void main(String[] args) {
		System.out.println("Iniciando aplicação Spring Boot...");
		SpringApplication.run(DiversidadeApplication.class, args);
	}
}


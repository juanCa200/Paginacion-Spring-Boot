package com.spring.boot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiRestCompletaApplication {

	@Bean
	public ModelMapper mapear() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApiRestCompletaApplication.class, args);
	}

}

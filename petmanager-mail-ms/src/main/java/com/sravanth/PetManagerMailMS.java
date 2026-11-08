package com.sravanth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:messages.properties")
@SpringBootApplication
public class PetManagerMailMS {
	public static void main(String[] args) {
		SpringApplication.run(PetManagerMailMS.class, args);
	}

}
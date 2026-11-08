package com.sravanth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer 
@SpringBootApplication
public class PetManagerConfigServer {
	public static void main(String[] args) {
		SpringApplication.run(PetManagerConfigServer.class, args);
	}

}

package com.sravanth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer 
@SpringBootApplication
public class PetmanagerDiscoveryServer {

	public static void main(String[] args) {
		SpringApplication.run(PetmanagerDiscoveryServer.class, args);
	}

}

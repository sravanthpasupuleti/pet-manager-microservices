package com.sravanth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer 
@SpringBootApplication
public class Demo {
	public static void main(String[] args) {
		SpringApplication.run(Demo.class, args);
	}

}

package com.sravanth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@EnableFeignClients 
@PropertySource("classpath:messages.properties")
@SpringBootApplication
public class PetManagerOwnerMS {
	public static void main(String[] args) {
		SpringApplication.run(PetManagerOwnerMS.class, args);
	}

}

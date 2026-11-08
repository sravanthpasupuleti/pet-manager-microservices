package com.sravanth.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfig {

	@Primary 
	@Bean 
	RestClient.Builder restClientBuilder(){
		return RestClient.builder();
	}


	@Bean
	RestClient loadBalancedRestClient(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder builder) {
		return builder.build();
	}

	@Bean 
	@LoadBalanced 
	RestClient.Builder loadBalancedRestClientBuilder(){
		return RestClient.builder();
	}

}

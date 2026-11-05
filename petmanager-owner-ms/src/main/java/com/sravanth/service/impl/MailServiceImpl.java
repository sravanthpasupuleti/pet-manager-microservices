package com.sravanth.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.sravanth.dto.MailDTO;
import com.sravanth.service.MailService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

	private final RestClient loadBalancedRestClient;
	
	@Value("${mail.service.base.uri}")
	private String mailServiceBaseUrl;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		ResponseEntity<String> response = loadBalancedRestClient.post()
				.uri(mailServiceBaseUrl)
				.body(mailDTO)
				.retrieve()
				.toEntity(String.class);
		return response.getBody();
	}

}

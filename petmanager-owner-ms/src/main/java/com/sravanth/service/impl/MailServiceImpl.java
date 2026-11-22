package com.sravanth.service.impl;

import org.springframework.stereotype.Service;

import com.sravanth.config.MailClient;
import com.sravanth.dto.MailDTO;
import com.sravanth.service.MailService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

	private final MailClient mailClient;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		return mailClient.sendEmail(mailDTO);
	}

}

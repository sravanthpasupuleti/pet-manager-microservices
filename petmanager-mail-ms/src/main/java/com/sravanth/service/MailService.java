package com.sravanth.service;

import com.sravanth.dto.MailDTO;


public interface MailService {

	String sendEmail(MailDTO mailDTO);

}

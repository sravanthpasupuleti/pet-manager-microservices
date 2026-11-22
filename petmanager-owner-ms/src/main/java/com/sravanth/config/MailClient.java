package com.sravanth.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sravanth.dto.MailDTO;

@FeignClient (name = "petmanager-mail-ms")
public interface MailClient {

    @PostMapping ("/mails")
    String sendEmail(@RequestBody MailDTO mailDTO);
    
}

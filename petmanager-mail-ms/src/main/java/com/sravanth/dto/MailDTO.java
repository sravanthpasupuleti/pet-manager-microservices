package com.sravanth.dto;

import com.sravanth.enums.MailType;


public record MailDTO(String to, String firstName, String lastName, MailType category) {

}

package com.sravanth.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;


public record ErrorDTO(String message, HttpStatus error, Integer status, LocalDateTime timestamp) {

}

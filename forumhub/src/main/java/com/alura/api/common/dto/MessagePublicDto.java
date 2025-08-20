package com.alura.api.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessagePublicDto(String message, LocalDateTime timestamp) {

    @JsonCreator
    public MessagePublicDto(String message) {
        this(message, LocalDateTime.now());
    }
}

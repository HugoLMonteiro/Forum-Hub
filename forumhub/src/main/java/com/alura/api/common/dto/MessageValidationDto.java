package com.alura.api.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageValidationDto(List<ValidationContentDto> validations, LocalDateTime timestamp) {

    @JsonCreator
    public MessageValidationDto(List<ValidationContentDto> content) {
        this(content, LocalDateTime.now());
    }
}

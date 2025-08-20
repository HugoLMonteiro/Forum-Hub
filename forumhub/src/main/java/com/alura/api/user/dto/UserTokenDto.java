package com.alura.api.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserTokenDto(String token, String type) {

    @JsonCreator
    public UserTokenDto(String token) {
        this(token, "Bearer");
    }
}

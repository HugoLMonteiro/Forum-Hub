package com.alura.api.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRegisterDto(
    @NotBlank(message = "Username cannot be blank or null")
    @Size(min = 2, max = 35, message = "Size must be min 2 and max 35")
    String username,

    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,65}$",
        message = "Password must contain: 'Uppercase, Special and Digits Characters'"
    )
    String password
) { }

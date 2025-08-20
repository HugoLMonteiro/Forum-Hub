package com.alura.api.topic.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TopicUpdateDto(
    @NotBlank(message = "Cannot be blank or null")
    @Size(min = 5, max = 100, message = "Size must be min 5 and max 100")
    String title,

    @NotBlank(message = "Cannot be blank or null")
    @Size(min = 1, max = 500, message = "Size must be min 1 and max 500")
    String message,

    LocalDateTime updatedAt
) {
    @JsonCreator
    public TopicUpdateDto(String title, String message) {
        this(title, message, LocalDateTime.now());
    }
}

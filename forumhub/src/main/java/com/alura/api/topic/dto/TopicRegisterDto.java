package com.alura.api.topic.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.alura.api.topic.model.Course;
import com.alura.api.topic.model.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TopicRegisterDto(
    @NotBlank(message = "Cannot be blank or null")
    @Size(min = 5, max = 100, message = "Size must be min 5 and max 100")
    String title,

    @NotBlank(message = "Cannot be blank or null")
    @Size(min = 1, max = 500, message = "Size must be min 1 and max 500")
    String message,

    @NotNull(message = "Cannot be null")
    Course course,

    State state,

    LocalDateTime createdAt
) {
    @JsonCreator
    public TopicRegisterDto(String title, String message, Course course) {
        this(title, message, course, State.OPEN, LocalDateTime.now());
    }
}

package com.alura.api.topic.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.alura.api.topic.model.Course;
import com.alura.api.topic.model.State;
import com.alura.api.user.dto.UserPublicDto;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TopicPublicDto(
    UUID id,
    String title,
    String message,
    String autor,
    State state,
    String course,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    @JsonCreator
    public TopicPublicDto(
        UUID id,
        String title,
        String message,
        @NonNull UserPublicDto author,
        State state,
        @NonNull Course course,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this(
            id,
            title,
            message,
            author.username(),
            state,
            course.title(),
            createdAt,
            updatedAt
        );
    }
}

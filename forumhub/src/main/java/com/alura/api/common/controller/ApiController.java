package com.alura.api.common.controller;

import com.alura.api.common.dto.MessagePublicDto;
import com.alura.api.common.dto.MessageValidationDto;
import com.alura.api.common.dto.ValidationContentDto;
import com.alura.api.topic.util.exception.TopicException;
import com.alura.api.user.util.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiController {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Void> handlerConstraintViolationException() {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageValidationDto> handlerMethodArgumentNotValidException(
        @NonNull MethodArgumentNotValidException exception
    ) {
        List<FieldError> erros = exception.getFieldErrors();
        List<ValidationContentDto> messages = new ArrayList<>(erros.size());

        for (FieldError error : erros) {
            messages.add(new ValidationContentDto(
                error.getRejectedValue(),
                error.getField(),
                error.getDefaultMessage()
            ));
        }

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new MessageValidationDto(messages));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<MessagePublicDto> handlerUsernameNotFoundException(
        @NonNull UsernameNotFoundException exception
    ) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new MessagePublicDto(exception.getMessage()));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MessagePublicDto> handlerUserException(
        @NonNull UserException exception
    ) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new MessagePublicDto(exception.getMessage()));
    }

    @ExceptionHandler(TopicException.class)
    public ResponseEntity<MessagePublicDto> handlerTopicException(
        @NonNull TopicException exception
    ) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new MessagePublicDto(exception.getMessage()));
    }
}

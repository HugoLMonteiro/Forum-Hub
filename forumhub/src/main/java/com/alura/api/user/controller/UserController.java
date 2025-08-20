package com.alura.api.user.controller;

import com.alura.api.common.config.SwaggerConfig;
import com.alura.api.user.dto.UserPublicDto;
import com.alura.api.user.dto.UserRegisterDto;
import com.alura.api.user.dto.UserTokenDto;
import com.alura.api.user.model.User;
import com.alura.api.user.service.UserService;
import com.alura.api.user.util.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users")
@AllArgsConstructor
public class UserController {
    private UserService service;
    private final UserMapper mapper;

    @PostMapping("/auth/register")
    @Operation(summary = "User Register")
    public ResponseEntity<UserPublicDto> register(@Valid @RequestBody UserRegisterDto body) {
        User entity = service.register(body);
        UserPublicDto response = mapper.toPublic(entity);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .headers(this.buildLocationHeader(response))
            .body(response);
    }

    @PostMapping("/auth/login")
    @Operation(
        summary = "User Login",
        security = @SecurityRequirement(name = SwaggerConfig.BASIC_NAME)
    )
    public ResponseEntity<UserTokenDto> login(Authentication auth) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(service.login(auth));
    }

    @PreAuthorize("hasAuthority('BASE_USER')")
    @GetMapping("/{id}")
    @Operation(
        summary = "Read User by ID",
        security = @SecurityRequirement(name = SwaggerConfig.BEARER_NAME)
    )
    public ResponseEntity<UserPublicDto> readById(@PathVariable UUID id) {
        User entity = service.readById(id);
        UserPublicDto response = mapper.toPublic(entity);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @NonNull
    private HttpHeaders buildLocationHeader(@NonNull UserPublicDto response) {
        URI location = UriComponentsBuilder
            .fromPath("/api/v1/users")
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return headers;
    }
}

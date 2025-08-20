package com.alura.api.user.util.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.alura.api.user.dto.UserPublicDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    private final ObjectMapper mapper;

    public <T>UserPublicDto toPublic(T obj) {
        return mapper.convertValue(obj, UserPublicDto.class);
    }
}

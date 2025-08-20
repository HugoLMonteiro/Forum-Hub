package com.alura.api.topic.util.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.alura.api.topic.dto.TopicPublicDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TopicMapper {
    private final ObjectMapper mapper;

    public <T>TopicPublicDto toPublicDto(T obj) {
        return mapper.convertValue(obj, TopicPublicDto.class);
    }
}

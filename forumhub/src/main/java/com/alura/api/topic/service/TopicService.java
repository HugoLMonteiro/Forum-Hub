package com.alura.api.topic.service;

import com.alura.api.security.service.JwtService;
import com.alura.api.topic.dto.TopicRegisterDto;
import com.alura.api.topic.dto.TopicUpdateDto;
import com.alura.api.topic.model.Topic;
import com.alura.api.topic.repository.TopicRepository;
import com.alura.api.topic.util.exception.TopicException;
import com.alura.api.user.model.User;
import com.alura.api.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TopicService {
    private final UserService userService;
    private final JwtService jwtService;
    private final TopicRepository repository;

    @Transactional
    public Topic create(TopicRegisterDto data, String token) {
        User author = (User) userService.loadUserByUsername(jwtService.decoder(token));
        Topic entity = new Topic(data, author);

        return repository.save(entity);
    }

    public Topic readById(UUID id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new TopicException("Topic not found"));
    }

    public List<Topic> readByCourseName(String name) {
        return repository
            .findAll()
            .stream()
            .filter(entity ->
                entity.getCourse().title().toLowerCase().contains(name)
            )
            .toList();
    }

    public List<Topic> readFirstsByDate(int limit) {
        return repository
            .findAll()
            .stream()
            .sorted(Comparator.comparing(Topic::getCreatedAt))
            .limit(limit)
            .toList();
    }

    public Page<Topic> readPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Topic> readAll() {
        return repository.findAll();
    }

    @Transactional
    public Topic update(UUID id, @NonNull TopicUpdateDto data) {
        Topic entity = this.readById(id);
        entity.setTitle(data.title());
        entity.setMessage(data.message());
        entity.setUpdatedAt(data.updatedAt());

        return repository.save(entity);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

package com.alura.api.topic.model;

import com.alura.api.topic.dto.TopicRegisterDto;
import com.alura.api.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User author;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "course", nullable = false)
    @Enumerated(EnumType.STRING)
    private Course course;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Topic() {}

    public Topic(@NonNull TopicRegisterDto data, User author) {
        this.title = data.title();
        this.message = data.message();
        this.author = author;
        this.state = data.state();
        this.course = data.course();
        this.createdAt = data.createdAt();
    }
}

package com.alura.api.topic.model;

public enum Course {
    BASIC_JAVA("Java Básico e Orientação a Objetos"),
    ADVANCED_JAVA("Java Avançado: Collections, Streams e Lambda"),
    SPRING_BOOT("Spring Boot: Desenvolvimento de APIs REST"),
    SPRING_SECURITY("Spring Security: Autenticação e Autorização"),
    SPRING_JPA("JPA e Hibernate: Persistência de Dados"),
    MICROSERVICES("Microsserviços com Spring Cloud"),
    AUTOMATED_TESTING("Testes Automatizados com JUnit e Mockito"),
    JAVA_FOR_WEB("Java para Aplicações Web com Thymeleaf"),
    JAVA_FOR_MESSAGING("Kafka e Mensageiria com Java"),
    JAVA_FOR_CLOUD("Java para Cloud: AWS e Docker");

    private final String title;

    Course(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }
}

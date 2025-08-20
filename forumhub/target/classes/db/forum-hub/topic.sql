CREATE TABLE topic
(
    id         BINARY(16)   NOT NULL,
    title      VARCHAR(100) NOT NULL,
    message    TEXT         NOT NULL,
    user_fk    BINARY(16)   NULL,
    state      VARCHAR(255) NOT NULL,
    course     VARCHAR(255) NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NULL,
    CONSTRAINT pk_topic PRIMARY KEY (id)
);

ALTER TABLE topic
    ADD CONSTRAINT FK_TOPIC_ON_USER_FK FOREIGN KEY (user_fk) REFERENCES user (id);
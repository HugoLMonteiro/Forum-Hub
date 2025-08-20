CREATE TABLE user
(
    id       BINARY(16)   NOT NULL,
    username VARCHAR(35)  NOT NULL,
    password VARCHAR(60)  NOT NULL,
    `role`   VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);
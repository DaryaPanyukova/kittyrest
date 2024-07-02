CREATE TABLE users
(
    id       BIGINT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    owner_id BIGINT              NOT NULL,
    roles    VARCHAR(50)         NOT NULL
);
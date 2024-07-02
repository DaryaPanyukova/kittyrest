CREATE TABLE cats
(
    id         BIGINT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    birth_date DATE,
    breed      VARCHAR(255),
    color      VARCHAR(50),
    image      BYTEA,
    owner_id   BIGINT
);
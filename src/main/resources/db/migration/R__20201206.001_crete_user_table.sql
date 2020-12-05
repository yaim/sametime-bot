CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT PRIMARY KEY,
    name     VARCHAR(256),
    created  timestamptz NOT NULL,
    updated  timestamptz NOT NULL
);

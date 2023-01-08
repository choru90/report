-- init.sql

create table health (
    id          BIGINT NOT NULL,
    name        VARCHAR(32) NOT NULL,
    created_at           DATETIME NOT NULL,
    updated_at           DATETIME NULL,
    PRIMARY KEY (id)
);
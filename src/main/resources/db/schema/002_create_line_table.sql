--liquibase formatted sql

--changeset scholanova:1
CREATE TABLE IF NOT EXISTS LINES (
  ID                  SERIAL          NOT NULL,
  NAME                VARCHAR(255)    NOT NULL,
  PRIMARY KEY (ID)
);

--liquibase formatted sql

--changeset scholanova:1
CREATE TABLE IF NOT EXISTS STATIONS (
  ID                  SERIAL          NOT NULL,
  NAME                VARCHAR(255)    NOT NULL,
  CITY                VARCHAR(255)    NOT NULL,
  COUNTRY             VARCHAR(255)    NOT NULL,
  PRIMARY KEY (ID)
);

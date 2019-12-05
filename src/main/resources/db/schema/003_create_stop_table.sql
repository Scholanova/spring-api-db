--liquibase formatted sql

--changeset scholanova:1
CREATE TABLE IF NOT EXISTS STOPS (
  ID                  SERIAL          NOT NULL,
  POSITION            NUMERIC         NOT NULL,
  IS_TERMINUS         BOOLEAN         NOT NULL,
  ID_STATION          INTEGER         NOT NULL,
  ID_LINE             INTEGER         NOT NULL,

  FOREIGN KEY (ID_STATION) REFERENCES STATIONS(ID),
  FOREIGN KEY (ID_LINE) REFERENCES LINES(ID),
  PRIMARY KEY (ID)
);


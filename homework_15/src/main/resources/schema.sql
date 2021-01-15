DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(
    ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(
    ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(
    ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    STATUS INTEGER NOT NULL DEFAULT 1,
    ID_AUTHOR BIGINT references AUTHORS (ID) on delete cascade,
    ID_GENRE BIGINT references GENRES (ID) on delete cascade
);

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS(
    ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ID_BOOKS BIGINT references BOOKS (ID) on delete cascade,
    TEXT_COMMENT VARCHAR(255) NOT NULL
);
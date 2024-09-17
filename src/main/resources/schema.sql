DROP TABLE IF EXISTS article;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_sequence START WITH 100000;

CREATE TABLE article
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_sequence'),
    created TIMESTAMP           DEFAULT now() NOT NULL,
    title   VARCHAR                           NOT NULL,
    content TEXT                              NOT NULL,
    preview TEXT                              NOT NULL
)

--liquibase formatted sql

--changeset author:02-insert-values.sql

INSERT INTO genres (name)
VALUES ('Комедия'),
       ('Драма'),
       ('Мультфильм'),
       ('Триллер'),
       ('Документальный'),
       ('Боевик');

INSERT INTO mpa (name)
VALUES ('G'),
       ('PG'),
       ('PG-13'),
       ('R'),
       ('NC-17');

--rollback DELETE FROM genres WHERE name IN ('Комедия', 'Драма', 'Мультфильм', 'Триллер', 'Документальный', 'Боевик');
--rollback DELETE FROM mpa WHERE name IN ('G', 'PG', 'PG13', 'R', 'NC17');

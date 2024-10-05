--liquibase formatted sql

--changeset author:Kozlov Nikita

insert into genres (name)
values ('Комедия'),
       ('Драма'),
       ('Мультфильм'),
       ('Триллер'),
       ('Документальный'),
       ('Боевик');

insert into mpa (name)
values ('G'),
       ('PG'),
       ('PG-13'),
       ('R'),
       ('NC-17');

--rollback delete from genres where name in ('Комедия', 'Драма', 'Мультфильм', 'Триллер', 'Документальный', 'Боевик');
--rollback delete from mpa where name in ('G', 'PG', 'PG13', 'R', 'NC17');

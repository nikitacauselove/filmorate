--liquibase formatted sql

--changeset author:02-insert-values.sql

insert into genres (id, name)
values (1, 'Комедия'),
       (2, 'Драма'),
       (3, 'Мультфильм'),
       (4, 'Триллер'),
       (5, 'Документальный'),
       (6, 'Боевик');

insert into mpa (id, name)
values (1, 'G'),
       (2, 'PG'),
       (3, 'PG13'),
       (4, 'R'),
       (5, 'NC17');

--rollback delete from genres where id between 1 and 6;
--rollback delete from mpa where id between 1 and 5;

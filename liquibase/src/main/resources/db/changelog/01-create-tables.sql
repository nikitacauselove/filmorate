--liquibase formatted sql

--changeset author:01-create-tables.sql

create table genres (
    id   bigserial   not null,
    name varchar(32) not null,
    constraint genres_pkey primary key (id)
);

create table mpa (
    id   bigserial   not null,
    name varchar(32) not null,
    constraint mpa_pkey primary key (id)
);

create table directors (
    id   bigserial   not null,
    name varchar(32) not null,
    constraint directors_pkey primary key (id)
);

create table films (
    id           bigserial                not null,
    name         varchar(32)              not null,
    description  text                     not null,
    release_date timestamp with time zone not null,
    duration     integer                  not null,
    mpa_id       bigint                   not null,
    likes_amount integer                  not null,
    constraint films_pkey primary key (id),
    foreign key (mpa_id) references mpa(id) on delete restrict
);

create table users (
    id       bigserial                not null,
    email    varchar(32)              not null,
    login    varchar(32)              not null,
    name     varchar(32),
    birthday timestamp with time zone not null,
    constraint users_pkey primary key (id)
);

create table events (
    id         bigserial                not null,
    created    timestamp with time zone not null,
    user_id    bigint                   not null,
    event_type varchar(16)              not null,
    operation  varchar(16)              not null,
    entity_id  bigint                   not null,
    constraint events_pkey primary key (id),
    foreign key (user_id) references users(id) on delete cascade
);

create table reviews (
    id          bigserial not null,
    content     text      not null,
    is_positive boolean   not null,
    user_id     bigint    not null,
    film_id     bigint    not null,
    useful      integer   not null,
    constraint reviews_pkey primary key (id),
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (film_id) references films(id) on delete cascade
);

create table film_genres (
    film_id  bigint not null,
    genre_id bigint not null,
    constraint film_genres_pkey primary key (film_id, genre_id),
    foreign key (film_id) references films(id) on delete cascade,
    foreign key (genre_id) references genres(id) on delete cascade
);

create table film_likes (
    film_id bigint not null,
    user_id bigint not null,
    constraint film_likes_pkey primary key (film_id, user_id),
    foreign key (film_id) references films(id) on delete cascade,
    foreign key (user_id) references users(id) on delete cascade
);

create table film_directors (
    film_id     bigint not null,
    director_id bigint not null,
    constraint film_directors_pkey primary key (film_id, director_id),
    foreign key (film_id) references films(id) on delete cascade,
    foreign key (director_id) references directors(id) on delete cascade
);

create table friendship (
    requesting_user_id bigint not null,
    receiving_user_id  bigint not null,
    constraint friendship_pkey primary key (requesting_user_id, receiving_user_id),
    foreign key (requesting_user_id) references users(id) on delete cascade,
    foreign key (receiving_user_id) references users(id) on delete cascade
);

create table review_marks (
    review_id bigint      not null,
    user_id   bigint      not null,
    mark_type varchar(16) not null,
    constraint review_marks_pkey primary key (review_id, user_id),
    foreign key (review_id) references reviews(id) on delete cascade,
    foreign key (user_id) references users(id) on delete cascade
);

--rollback drop table review_marks;
--rollback drop table friendship;
--rollback drop table film_directors;
--rollback drop table film_likes;
--rollback drop table film_genres;
--rollback drop table reviews;
--rollback drop table events;
--rollback drop table users;
--rollback drop table films;
--rollback drop table directors;
--rollback drop table mpa;
--rollback drop table genres;

--liquibase formatted sql

--changeset author:01-create-tables.sql

CREATE TABLE genre (
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name TEXT                                    NOT NULL,
    CONSTRAINT genre_pkey PRIMARY KEY (id)
);

CREATE TABLE mpa (
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name TEXT                                    NOT NULL,
    CONSTRAINT mpa_pkey PRIMARY KEY (id)
);

CREATE TABLE director (
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name TEXT                                    NOT NULL,
    CONSTRAINT director_pkey PRIMARY KEY (id)
);

CREATE TABLE film (
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name         TEXT                                    NOT NULL,
    description  TEXT                                    NOT NULL,
    release_date TIMESTAMP WITH TIME ZONE                NOT NULL,
    duration     INTEGER                                 NOT NULL,
    mpa_id       BIGINT                                  NOT NULL,
    likes_amount INTEGER                                 NOT NULL,
    CONSTRAINT film_pkey PRIMARY KEY (id),
    FOREIGN KEY (mpa_id) REFERENCES mpa (id) ON DELETE RESTRICT
);

CREATE TABLE users (
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    email    TEXT                                    NOT NULL,
    login    TEXT                                    NOT NULL,
    name     TEXT,
    birthday TIMESTAMP WITH TIME ZONE                NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE event (
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created    TIMESTAMP WITH TIME ZONE                NOT NULL,
    user_id    BIGINT                                  NOT NULL,
    event_type TEXT                                    NOT NULL,
    operation  TEXT                                    NOT NULL,
    entity_id  BIGINT                                  NOT NULL,
    CONSTRAINT event_pkey PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE review (
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    content     TEXT                                    NOT NULL,
    is_positive BOOLEAN                                 NOT NULL,
    user_id     BIGINT                                  NOT NULL,
    film_id     BIGINT                                  NOT NULL,
    useful      INTEGER                                 NOT NULL,
    CONSTRAINT review_pkey PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (film_id) REFERENCES film (id) ON DELETE CASCADE
);

CREATE TABLE film_genre (
    film_id  BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    CONSTRAINT film_genre_pkey PRIMARY KEY (film_id, genre_id),
    FOREIGN KEY (film_id) REFERENCES film (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre (id) ON DELETE CASCADE
);

CREATE TABLE film_like (
    film_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT film_like_pkey PRIMARY KEY (film_id, user_id),
    FOREIGN KEY (film_id) REFERENCES film (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE film_director (
    film_id     BIGINT NOT NULL,
    director_id BIGINT NOT NULL,
    CONSTRAINT film_director_pkey PRIMARY KEY (film_id, director_id),
    FOREIGN KEY (film_id) REFERENCES film (id) ON DELETE CASCADE,
    FOREIGN KEY (director_id) REFERENCES director (id) ON DELETE CASCADE
);

CREATE TABLE friendship (
    requesting_user_id BIGINT NOT NULL,
    receiving_user_id  BIGINT NOT NULL,
    CONSTRAINT friendship_pkey PRIMARY KEY (requesting_user_id, receiving_user_id),
    FOREIGN KEY (requesting_user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (receiving_user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE review_mark (
    review_id BIGINT NOT NULL,
    user_id   BIGINT NOT NULL,
    mark_type TEXT   NOT NULL,
    CONSTRAINT review_mark_pkey PRIMARY KEY (review_id, user_id),
    FOREIGN KEY (review_id) REFERENCES review (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

--rollback DROP TABLE review_mark;
--rollback DROP TABLE friendship;
--rollback DROP TABLE film_director;
--rollback DROP TABLE film_like;
--rollback DROP TABLE film_genre;
--rollback DROP TABLE review;
--rollback DROP TABLE event;
--rollback DROP TABLE users;
--rollback DROP TABLE film;
--rollback DROP TABLE director;
--rollback DROP TABLE mpa;
--rollback DROP TABLE genre;

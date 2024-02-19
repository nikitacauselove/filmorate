DROP TABLE review_marks;
DROP TABLE friendship;
DROP TABLE film_directors;
DROP TABLE film_likes;
DROP TABLE film_genres;
DROP TABLE reviews;
DROP TABLE events;
DROP TABLE users;
DROP TABLE films;
DROP TABLE directors;
DROP TABLE mpa;
DROP TABLE genres;

CREATE TABLE IF NOT EXISTS genres (
    PRIMARY KEY (id),
    id   INT         NOT NULL,
    name VARCHAR(16) NOT NULL,
         UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS mpa (
    PRIMARY KEY (id),
    id   INT         NOT NULL,
    name VARCHAR(16) NOT NULL,
         UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS directors (
    PRIMARY KEY (id),
    id   INT         NOT NULL,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS films (
    PRIMARY KEY (id),
    id           INT         NOT NULL,
    name         VARCHAR(64) NOT NULL,
    description  VARCHAR(64) NOT NULL,
    release_date DATE        NOT NULL,
    duration     INT         NOT NULL,
    mpa_id       INT         NOT NULL,
                 FOREIGN KEY (mpa_id) REFERENCES mpa(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users (
    PRIMARY KEY (id),
    id       INT         NOT NULL,
    email    VARCHAR(64) NOT NULL,
    login    VARCHAR(64) NOT NULL,
    name     VARCHAR(64),
    birthday DATE        NOT NULL
);

CREATE TABLE IF NOT EXISTS events (
    PRIMARY KEY (id),
    id         INT                               NOT NULL,
    created    TIMESTAMP WITHOUT TIME ZONE       DEFAULT CURRENT_TIMESTAMP,
    user_id    INT                               NOT NULL,
    event_type ENUM ('FRIEND', 'LIKE', 'REVIEW') NOT NULL,
    operation  ENUM ('ADD', 'REMOVE', 'UPDATE')  NOT NULL,
    entity_id  INT                               NOT NULL,
               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reviews (
    PRIMARY KEY (id),
    id          INT         NOT NULL,
    content     VARCHAR(64) NOT NULL,
    is_positive BOOLEAN     NOT NULL,
    user_id     INT         NOT NULL,
    film_id     INT         NOT NULL,
    useful      INT         NOT NULL,
                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS film_genres (
    PRIMARY KEY (film_id, genre_id),
    film_id  INT NOT NULL,
    genre_id INT NOT NULL,
             FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE,
             FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS film_likes (
    PRIMARY KEY (film_id, user_id),
    film_id INT NOT NULL,
    user_id INT NOT NULL,
            FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE,
            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS film_directors (
    PRIMARY KEY (film_id, director_id),
    film_id     INT NOT NULL,
    director_id INT NOT NULL,
                FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE,
                FOREIGN KEY (director_id) REFERENCES directors(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS friendship (
    PRIMARY KEY (requesting_user_id, receiving_user_id),
    requesting_user_id INT NOT NULL,
    receiving_user_id  INT NOT NULL,
                       FOREIGN KEY (requesting_user_id) REFERENCES users(id) ON DELETE CASCADE,
                       FOREIGN KEY (receiving_user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS review_marks (
    PRIMARY KEY (review_id, user_id),
    review_id INT                      NOT NULL,
    user_id   INT                      NOT NULL,
    mark_type ENUM ('DISLIKE', 'LIKE') NOT NULL,
              FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE,
              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
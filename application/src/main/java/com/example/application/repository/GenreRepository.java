package com.example.application.repository;

import com.example.application.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для взаимодействия с жанрами фильмов.
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {

    String NOT_FOUND = "Жанр фильма с указанным идентификатором не найден";
}

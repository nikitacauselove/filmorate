package com.example.application.persistence.repository;

import com.example.application.persistence.model.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для взаимодействия с жанрами фильмов.
 */
@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    String NOT_FOUND = "Жанр фильма с указанным идентификатором не найден";

}

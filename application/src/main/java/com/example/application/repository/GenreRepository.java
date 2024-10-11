package com.example.application.repository;

import com.example.application.repository.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для взаимодействия с жанрами фильмов.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

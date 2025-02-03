package com.example.application.persistence;

import com.example.application.domain.Genre;

import java.util.List;

/**
 * Сервис для взаимодействия с жанрами фильмов.
 */
public interface GenrePersistenceService {

    /**
     * Получение информации о жанре фильма.
     *
     * @param id идентификатор жанра фильма
     */
    Genre findById(Long id);

    /**
     * Получение списка всех жанров фильмов.
     */
    List<Genre> findAll();
}

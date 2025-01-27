package com.example.application.service;

import com.example.application.repository.entity.Genre;

import java.util.List;

/**
 * Сервис для взаимодействия с жанрами фильмов.
 */
public interface GenreService {

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

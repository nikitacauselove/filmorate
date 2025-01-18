package com.example.application.service;

import com.example.application.repository.entity.Genre;

import java.util.List;

/**
 * Сервис для взаимодействия с жанрами фильмов.
 */
public interface GenreService {

    /**
     * Получение информации о жанре.
     * @param id идентификатор жанра
     */
    Genre findById(Long id);

    /**
     * Получение списка всех жанров.
     */
    List<Genre> findAll();
}

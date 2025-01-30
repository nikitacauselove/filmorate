package com.example.application.service;

import com.example.application.domain.Director;

import java.util.List;

/**
 * Сервис для взаимодействия с режиссёрами.
 */
public interface DirectorService {

    /**
     * Добавление нового режиссёра.
     *
     * @param director информация о режиссёре
     */
    Director create(Director director);

    /**
     * Обновление информации о режиссёре.
     *
     * @param director информация о режиссёре
     */
    Director update(Director director);

    /**
     * Получение информации о режиссёре.
     *
     * @param id идентификатор режиссёра
     */
    Director findById(Long id);

    /**
     * Получение списка всех режиссёров.
     */
    List<Director> findAll();

    /**
     * Удаление режиссёра.
     *
     * @param id идентификатор режиссёра
     */
    void deleteById(Long id);
}

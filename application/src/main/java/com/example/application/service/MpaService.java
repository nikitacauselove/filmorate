package com.example.application.service;

import com.example.application.repository.entity.Mpa;

import java.util.List;

/**
 * Сервис для взаимодействия с системой рейтингов Американской киноассоциации.
 */
public interface MpaService {

    /**
     * Получение информации о MPA.
     * @param id идентификатор рейтинга
     */
    Mpa findById(Long id);

    /**
     * Получение списка всех MPA.
     */
    List<Mpa> findAll();
}

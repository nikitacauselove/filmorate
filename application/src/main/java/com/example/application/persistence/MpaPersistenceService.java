package com.example.application.persistence;

import com.example.application.domain.Mpa;

import java.util.List;

/**
 * Сервис для взаимодействия с рейтингами Американской киноассоциации.
 */
public interface MpaPersistenceService {

    /**
     * Получение информации о рейтинге Американской киноассоциации.
     *
     * @param id идентификатор рейтинга Американской киноассоциации
     */
    Mpa findById(Long id);

    /**
     * Получение списка всех рейтингов Американской киноассоциации.
     */
    List<Mpa> findAll();
}

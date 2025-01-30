package com.example.application.persistence;

import com.example.api.model.type.MarkType;
import com.example.application.domain.Review;

import java.util.List;

/**
 * Сервис для взаимодействия с рецензиями.
 */
public interface ReviewPersistenceService {

    /**
     * Добавление новой рецензии.
     *
     * @param review информация о рецензии
     */
    Review create(Review review);

    /**
     * Обновление рецензии.
     *
     * @param review информация о рецензии
     */
    Review update(Review review);

    /**
     * Получение информации о рецензии.
     *
     * @param id идентификатор рецензии
     */
    Review findById(Long id);

    /**
     * Получение списка всех рецензий.
     *
     * @param filmId идентификатор фильма
     * @param count количество элементов для отображения
     */
    List<Review> findAll(Long filmId, Integer count);

    /**
     * Удаление рецензии.
     *
     * @param id идентификатор рецензии
     */
    void deleteById(Long id);

    /**
     * Добавление оценки.
     *
     * @param id идентификатор рецензии
     * @param userId идентификатор пользователя
     * @param markType тип оценки
     */
    void addMark(Long id, Long userId, MarkType markType);

    /**
     * Удаление оценки.
     *
     * @param id идентификатор рецензии
     * @param userId идентификатор пользователя
     * @param markType тип оценки
     */
    void deleteMark(Long id, Long userId, MarkType markType);

}

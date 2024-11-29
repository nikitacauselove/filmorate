package com.example.application.service;

import com.example.api.dto.enums.MarkType;
import com.example.application.repository.entity.Review;

import java.util.List;

/**
 * Сервис для взаимодействия с рецензиями на фильмы.
 */
public interface ReviewService {

    /**
     * Добавление новой рецензии.
     * @param review информация о рецензии
     */
    Review create(Review review);

    /**
     * Обновление рецензии.
     * @param review информация о рецензии
     */
    Review update(Review review);

    /**
     * Получение информации о рецензии.
     * @param id идентификатор рецензии
     */
    Review findById(Long id);

    /**
     * Получение списка всех рецензий.
     * @param filmId идентификатор фильма
     * @param count максимальное количество элементов
     */
    List<Review> findAll(Long filmId, Integer count);

    /**
     * Удаление рецензии.
     * @param id идентификатор рецензии
     * @param userId идентификатор пользователя
     */
    void deleteById(Long id, Long userId);

    /**
     * Добавление реакции.
     * @param id идентификатор рецензии
     * @param userId идентификатор пользователя
     * @param markType тип оценки
     */
    void addMark(Long id, Long userId, MarkType markType);

    /**
     * Удаление реакции.
     * @param id идентификатор рецензии
     * @param userId идентификатор пользователя
     * @param markType тип оценки
     */
    void deleteMark(Long id, Long userId, MarkType markType);
}

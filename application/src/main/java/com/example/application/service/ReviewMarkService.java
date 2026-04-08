package com.example.application.service;

import com.example.application.entity.ReviewMark;
import com.example.application.entity.ReviewMarkId;

/**
 * Сервис для взаимодействия с оценками рецензий.
 */
public interface ReviewMarkService {

    /**
     * Добавление новой оценки рецензии.
     *
     * @param reviewMark информация об оценки рецензии
     */
    ReviewMark create(ReviewMark reviewMark);

    /**
     * Удаление оценки рецензии.
     *
     * @param id идентификатор оценки рецензии
     */
    void deleteById(ReviewMarkId id);
}

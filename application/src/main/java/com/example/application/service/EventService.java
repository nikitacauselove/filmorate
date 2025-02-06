package com.example.application.service;

import com.example.application.entity.Event;

import java.util.List;

/**
 * Сервис для взаимодействия с действиями пользователей.
 */
public interface EventService {

    /**
     * Получение списка всех действий пользователя.
     *
     * @param userId идентификатор пользователя
     */
    List<Event> findAllByUserId(Long userId);
}

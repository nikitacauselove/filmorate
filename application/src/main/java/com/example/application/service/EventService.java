package com.example.application.service;

import com.example.application.domain.Event;

import java.util.List;

/**
 * Сервис для взаимодействия с действиями пользователей.
 */
public interface EventService {

    /**
     * Добавление нового действия пользователя.
     *
     * @param event информация о действии пользователя
     */
    Event create(Event event);

    /**
     * Получение списка всех действий пользователя.
     *
     * @param userId идентификатор пользователя
     */
    List<Event> findAllByUserId(Long userId);
}

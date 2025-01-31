package com.example.application.service;

import com.example.application.domain.Event;
import com.example.application.domain.EventType;
import com.example.application.domain.Operation;

import java.util.List;

/**
 * Сервис для взаимодействия с действиями пользователей.
 */
public interface EventService {

    /**
     * Добавление нового действия пользователя.
     *
     * @param userId    идентификатор пользователя
     * @param eventType тип действия
     * @param operation тип операции
     * @param entityId  идентификатор сущности
     */
    Event create(Long userId, EventType eventType, Operation operation, Long entityId);

    /**
     * Получение списка всех действий пользователя.
     *
     * @param userId идентификатор пользователя
     */
    List<Event> findAllByUserId(Long userId);
}

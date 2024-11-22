package com.example.application.service;

import com.example.api.dto.enums.EventOperation;
import com.example.api.dto.enums.EventType;
import com.example.application.repository.entity.Event;

import java.util.List;

/**
 * Сервис для взаимодействия с действими пользователей.
 */
public interface EventService {

    /**
     * Добавление нового действия пользователя.
     * @param userId идентификатор пользователя
     * @param eventType тип действия
     * @param operation тип операции
     * @param entityId идентификатор сущности, с которой происходит взаимодействие
     */
    Event create(Long userId, EventType eventType, EventOperation operation, Long entityId);

    /**
     * Получение списка всех действий пользователя.
     * @param id идентификатор пользователя
     */
    List<Event> findAllByUserId(Long id);
}
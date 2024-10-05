package com.example.application.service;

import com.example.api.dto.enums.EventOperation;
import com.example.api.dto.enums.EventType;
import com.example.application.repository.entity.Event;

import java.util.List;

public interface EventService {

    Event create(Long userId, EventType eventType, EventOperation operation, Long entityId);

    List<Event> findAllByUserId(Long id);
}

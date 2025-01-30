package com.example.application.service.impl;

import com.example.api.model.type.EventType;
import com.example.api.model.type.Operation;
import com.example.application.domain.Event;
import com.example.application.persistence.EventPersistenceService;
import com.example.application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventPersistenceService eventPersistenceService;

    @Override
    public Event create(Long userId, EventType eventType, Operation operation, Long entityId) {
        return eventPersistenceService.create(userId, eventType, operation, entityId);
    }

    @Override
    public List<Event> findAllByUserId(Long userId) {
        return eventPersistenceService.findAllByUserId(userId);
    }
}

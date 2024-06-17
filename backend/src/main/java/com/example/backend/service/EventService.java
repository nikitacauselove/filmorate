package com.example.backend.service;

import com.example.backend.repository.entity.Event;

import java.util.List;

public interface EventService {

    Event create(Long userId, Event.EventType eventType, Event.Operation operation, Long entityId);

    List<Event> findAllByUserId(Long id);
}

package com.example.application.service.impl;

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
    public Event create(Event event) {
        return eventPersistenceService.create(event);
    }

    @Override
    public List<Event> findAllByUserId(Long userId) {
        return eventPersistenceService.findAllByUserId(userId);
    }
}

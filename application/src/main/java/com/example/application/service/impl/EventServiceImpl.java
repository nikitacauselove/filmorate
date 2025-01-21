package com.example.application.service.impl;

import com.example.api.dto.enums.EventType;
import com.example.api.dto.enums.Operation;
import com.example.application.repository.entity.Event;
import com.example.application.repository.EventRepository;
import com.example.application.repository.UserRepository;
import com.example.application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public Event create(Long userId, EventType eventType, Operation operation, Long entityId) {
        return eventRepository.save(Event.builder()
                .userId(userId)
                .eventType(eventType)
                .operation(operation)
                .entityId(entityId)
                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAllByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден");
        }
        return eventRepository.findAllByUserId(userId);
    }
}

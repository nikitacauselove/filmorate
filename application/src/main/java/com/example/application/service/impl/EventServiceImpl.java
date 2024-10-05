package com.example.application.service.impl;

import com.example.api.dto.enums.EventOperation;
import com.example.api.dto.enums.EventType;
import com.example.application.repository.entity.Event;
import com.example.application.repository.EventRepository;
import com.example.application.repository.UserRepository;
import com.example.application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Event create(Long userId, EventType eventType, EventOperation operation, Long entityId) {
        return eventRepository.save(Event.builder()
                .timestamp(LocalDateTime.now())
                .userId(userId)
                .eventType(eventType)
                .operation(operation)
                .entityId(entityId)
                .build());
    }

    @Override
    @Transactional
    public List<Event> findAllByUserId(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }

        return eventRepository.findAllByUserId(id);
    }
}

package com.example.application.persistence.impl;

import com.example.api.model.type.EventType;
import com.example.api.model.type.Operation;
import com.example.application.domain.Event;
import com.example.application.persistence.EventPersistenceService;
import com.example.application.persistence.mapper.EventEntityMapper;
import com.example.application.persistence.model.EventEntity;
import com.example.application.persistence.repository.EventRepository;
import com.example.application.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventPersistenceServiceImpls implements EventPersistenceService {

    private final EventEntityMapper eventEntityMapper;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public Event create(Long userId, EventType eventType, Operation operation, Long entityId) {
        EventEntity eventEntity = EventEntity.builder()
                .userId(userId)
                .eventType(eventType)
                .operation(operation)
                .entityId(entityId)
                .build();

        return eventEntityMapper.toDomain(eventRepository.save(eventEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAllByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, UserRepository.NOT_FOUND);
        }
        return eventEntityMapper.toDomain(eventRepository.findAllByUserId(userId));
    }
}

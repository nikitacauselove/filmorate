package com.example.application.persistence.impl;

import com.example.application.domain.Event;
import com.example.application.exception.NotFoundException;
import com.example.application.persistence.EventPersistenceService;
import com.example.application.persistence.mapper.EventEntityMapper;
import com.example.application.persistence.model.EventEntity;
import com.example.application.persistence.repository.EventRepository;
import com.example.application.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventPersistenceServiceImpls implements EventPersistenceService {

    private final EventEntityMapper eventEntityMapper;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public Event create(Event event) {
        EventEntity eventEntity = eventEntityMapper.toEntity(event);

        return eventEntityMapper.toDomain(eventRepository.save(eventEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAllByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(UserRepository.NOT_FOUND);
        }
        return eventEntityMapper.toDomain(eventRepository.findAllByUserId(userId));
    }
}

package com.example.backend.service.impl;

import com.example.backend.repository.entity.Event;
import com.example.backend.repository.EventRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.EventService;
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
    public Event create(Long userId, Event.EventType eventType, Event.Operation operation, Long entityId) {
        Event event = new Event(null, LocalDateTime.now(), userId, eventType, operation, entityId);

        return eventRepository.save(event);
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

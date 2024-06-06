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

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public List<Event> findAllByUserId(Long id) {
        if (userRepository.existsById(id)) {
            return eventRepository.findAllByUserId(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
    }
}

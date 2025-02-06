package com.example.application.service.impl;

import com.example.application.entity.Event;
import com.example.application.exception.NotFoundException;
import com.example.application.repository.EventRepository;
import com.example.application.repository.UserRepository;
import com.example.application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAllByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(UserRepository.NOT_FOUND);
        }
        return eventRepository.findAllByUserId(userId);
    }
}

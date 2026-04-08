package com.example.application.service.impl;

import com.example.application.entity.Event;
import com.example.application.entity.User;
import com.example.application.repository.EventRepository;
import com.example.application.service.EventService;
import com.example.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    @Lazy
    private final UserService userService;

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAllByUserId(Long userId) {
        User user = userService.findById(userId);

        return eventRepository.findAllByUserId(user.getId());
    }
}

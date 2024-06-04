package com.example.backend.service;

import com.example.backend.entity.Event;

import java.util.List;

public interface EventService {

    Event create(Event event);

    List<Event> findAllByUserId(Long id);
}

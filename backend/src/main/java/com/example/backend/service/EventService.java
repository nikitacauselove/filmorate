package com.example.backend.service;

import com.example.backend.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> findAllByUserId(int id);
}

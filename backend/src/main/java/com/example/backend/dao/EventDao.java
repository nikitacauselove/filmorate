package com.example.backend.dao;

import com.example.backend.entity.Event;

import java.util.List;

public interface EventDao {

    void create(Event event);

    List<Event> findAllByUserId(int userId);
}

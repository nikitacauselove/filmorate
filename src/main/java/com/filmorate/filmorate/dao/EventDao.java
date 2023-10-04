package com.filmorate.filmorate.dao;

import com.filmorate.filmorate.model.Event;

import java.util.List;

public interface EventDao {
    void create(Event event);

    List<Event> findAllByUserId(int userId);
}

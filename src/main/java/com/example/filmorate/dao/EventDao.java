package com.example.filmorate.dao;

import com.example.filmorate.entity.Event;

import java.util.List;

public interface EventDao {

    void create(Event event);

    List<Event> findAllByUserId(int userId);
}

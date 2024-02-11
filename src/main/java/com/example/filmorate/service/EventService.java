package com.example.filmorate.service;

import com.example.filmorate.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> findAllByUserId(int id);
}

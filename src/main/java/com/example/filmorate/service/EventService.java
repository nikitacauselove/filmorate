package com.example.filmorate.service;

import com.example.filmorate.dao.EventDao;
import com.example.filmorate.dao.UserDao;
import com.example.filmorate.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class EventService {
    private final EventDao eventDao;
    private final UserDao userDao;

    public List<Event> findAllByUserId(int id) {
        if (userDao.existsById(id)) {
            return eventDao.findAllByUserId(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
    }
}

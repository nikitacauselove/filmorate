package com.example.backend.service.impl;

import com.example.backend.dao.EventDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.Event;
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

//    private final EventDao eventDao;
//    private final UserDao userDao;

    @Override
    @Transactional
    public List<Event> findAllByUserId(Long id) {
//        if (userDao.existsById(id)) {
//            return eventDao.findAllByUserId(id);
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
//        }
        return null;
    }
}

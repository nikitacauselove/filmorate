package com.example.filmorate.service.impl;

import com.example.filmorate.dao.EventDao;
import com.example.filmorate.dao.FilmDao;
import com.example.filmorate.dao.UserDao;
import com.example.filmorate.entity.Event;
import com.example.filmorate.entity.Film;
import com.example.filmorate.entity.User;
import com.example.filmorate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final EventDao eventDao;
    private final FilmDao filmDao;
    private final UserDao userDao;

    @Override
    @Transactional
    public User create(User user) {
        if (user.isValid()) {
            userDao.create(user);
        }
        return findById(user.getId());
    }

    @Override
    @Transactional
    public User update(User user) {
        if (user.isValid()) {
            userDao.update(user);
        }
        return findById(user.getId());
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void deleteById(int userId) {
        userDao.deleteById(userId);
    }

    @Override
    @Transactional
    public void addFriend(int id, int friendId) {
        if (userDao.existsById(id) & userDao.existsById(friendId)) {
            userDao.addFriend(id, friendId);
            eventDao.create(new Event(null, null, id, Event.EventType.FRIEND, Event.Operation.ADD, friendId));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
    }

    @Override
    @Transactional
    public void deleteFriend(int id, int friendId) {
        userDao.deleteFriend(id, friendId);
        eventDao.create(new Event(null, null, id, Event.EventType.FRIEND, Event.Operation.REMOVE, friendId));
    }

    @Override
    public List<User> findAllFriends(int id) {
        if (userDao.existsById(id)) {
            return userDao.findAllFriends(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
    }

    @Override
    public List<User> findCommonFriends(int id, int otherId) {
        return userDao.findCommonFriends(id, otherId);
    }

    @Override
    public List<Film> findRecommendations(int id) {
        return filmDao.findRecommendations(id);
    }
}

package com.filmorate.filmorate.service;

import com.filmorate.filmorate.dao.EventDao;
import com.filmorate.filmorate.dao.FilmDao;
import com.filmorate.filmorate.dao.UserDao;
import com.filmorate.filmorate.model.Event;
import com.filmorate.filmorate.model.Film;
import com.filmorate.filmorate.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class UserService {
    private final EventDao eventDao;
    private final FilmDao filmDao;
    private final UserDao userDao;

    @Transactional
    public User create(User user) {
        if (user.isValid()) {
            userDao.create(user);
        }
        return findById(user.getId());
    }

    @Transactional
    public User update(User user) {
        if (user.isValid()) {
            userDao.update(user);
        }
        return findById(user.getId());
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    public void deleteById(int userId) {
        userDao.deleteById(userId);
    }

    @Transactional
    public void addFriend(int id, int friendId) {
        if (userDao.existsById(id) & userDao.existsById(friendId)) {
            userDao.addFriend(id, friendId);
            eventDao.create(new Event(null, null, id, Event.EventType.FRIEND, Event.Operation.ADD, friendId));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
    }

    @Transactional
    public void deleteFriend(int id, int friendId) {
        userDao.deleteFriend(id, friendId);
        eventDao.create(new Event(null, null, id, Event.EventType.FRIEND, Event.Operation.REMOVE, friendId));
    }

    public List<User> findAllFriends(int id) {
        if (userDao.existsById(id)) {
            return userDao.findAllFriends(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
    }

    public List<User> findCommonFriends(int id, int otherId) {
        return userDao.findCommonFriends(id, otherId);
    }

    public List<Film> findRecommendations(int id) {
        return filmDao.findRecommendations(id);
    }
}

package com.example.filmorate.service;

import com.example.filmorate.dao.DirectorDao;
import com.example.filmorate.dao.EventDao;
import com.example.filmorate.dao.FilmDao;
import com.example.filmorate.dao.UserDao;
import com.example.filmorate.model.Event;
import com.example.filmorate.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class FilmService {
    private final DirectorDao directorDao;
    private final EventDao eventDao;
    private final FilmDao filmDao;
    private final UserDao userDao;

    @Transactional
    public Film create(Film film) {
        if (film.isValid()) {
            filmDao.create(film);
        }
        return findById(film.getId());
    }

    @Transactional
    public Film update(Film film) {
        if (film.isValid()) {
            filmDao.update(film);
        }
        return findById(film.getId());
    }

    public Film findById(int id) {
        return filmDao.findById(id);
    }

    public List<Film> findAll() {
        return filmDao.findAll();
    }

    public List<Film> findAllByDirectorId(int directorId, Film.SortBy sortBy) {
        if (directorDao.existsById(directorId)) {
            return filmDao.findAllByDirectorId(directorId, sortBy);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Режиссер с указанным идентификатором не найден.");
        }
    }

    @Transactional
    public void deleteById(int filmId) {
        filmDao.deleteById(filmId);
    }

    @Transactional
    public void addLike(int id, int userId) {
        filmDao.addLike(id, userId);
        eventDao.create(new Event(null, null, userId, Event.EventType.LIKE, Event.Operation.ADD, id));
    }

    @Transactional
    public void deleteLike(int id, int userId) {
        if (userDao.existsById(userId)) {
            filmDao.deleteLike(id, userId);
            eventDao.create(new Event(null, null, userId, Event.EventType.LIKE, Event.Operation.REMOVE, id));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
    }

    public List<Film> findCommon(int userId, int friendId) {
        return filmDao.findCommon(userId, friendId);
    }

    public List<Film> findPopular(int count, Optional<Integer> genreId, Optional<Integer> year) {
        return filmDao.findPopular(count, genreId, year);
    }

    public List<Film> search(String query, List<String> by) {
        return filmDao.search(query, by);
    }
}

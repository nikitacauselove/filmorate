package com.example.backend.service.impl;

import com.example.backend.dao.DirectorDao;
import com.example.backend.dao.EventDao;
import com.example.backend.dao.FilmDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.Event;
import com.example.backend.entity.Film;
import com.example.backend.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final DirectorDao directorDao;
    private final EventDao eventDao;
    private final FilmDao filmDao;
    private final UserDao userDao;

    @Override
    @Transactional
    public Film create(Film film) {
        filmDao.create(film);

        return findById(film.getId());
    }

    @Override
    @Transactional
    public Film update(Film film) {
        filmDao.update(film);

        return findById(film.getId());
    }

    @Override
    public Film findById(int id) {
        return filmDao.findById(id);
    }

    @Override
    public List<Film> findAll() {
        return filmDao.findAll();
    }

    @Override
    public List<Film> findAllByDirectorId(int directorId, Film.SortBy sortBy) {
        if (directorDao.existsById(directorId)) {
            return filmDao.findAllByDirectorId(directorId, sortBy);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Режиссер с указанным идентификатором не найден.");
        }
    }

    @Override
    @Transactional
    public void deleteById(int filmId) {
        filmDao.deleteById(filmId);
    }

    @Override
    @Transactional
    public void addLike(int id, int userId) {
        filmDao.addLike(id, userId);
        eventDao.create(new Event(null, null, userId, Event.EventType.LIKE, Event.Operation.ADD, id));
    }

    @Override
    @Transactional
    public void deleteLike(int id, int userId) {
        if (userDao.existsById(userId)) {
            filmDao.deleteLike(id, userId);
            eventDao.create(new Event(null, null, userId, Event.EventType.LIKE, Event.Operation.REMOVE, id));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
    }

    @Override
    public List<Film> findCommon(int userId, int friendId) {
        return filmDao.findCommon(userId, friendId);
    }

    @Override
    public List<Film> findPopular(int count, Optional<Integer> genreId, Optional<Integer> year) {
        return filmDao.findPopular(count, genreId, year);
    }

    @Override
    public List<Film> search(String query, List<String> by) {
        return filmDao.search(query, by);
    }
}

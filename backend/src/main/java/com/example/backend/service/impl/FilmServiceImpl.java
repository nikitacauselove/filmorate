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

//    private final DirectorDao directorDao;
//    private final EventDao eventDao;
//    private final FilmDao filmDao;
//    private final UserDao userDao;

    @Override
    @Transactional
    public Film create(Film film) {
//        filmDao.create(film);
//
//        return findById(film.getId());
        return null;
    }

    @Override
    @Transactional
    public Film update(Film film) {
//        filmDao.update(film);
//
//        return findById(film.getId());
        return null;
    }

    @Override
    public Film findById(Long id) {
//        return filmDao.findById(id);
        return null;
    }

    @Override
    public List<Film> findAll() {
//        return filmDao.findAll();
        return null;
    }

    @Override
    public List<Film> findAllByDirectorId(Long directorId, Film.SortBy sortBy) {
//        if (directorDao.existsById(directorId)) {
//            return filmDao.findAllByDirectorId(directorId, sortBy);
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Режиссер с указанным идентификатором не найден.");
//        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
//        filmDao.deleteById(id);
    }

    @Override
    @Transactional
    public void addLike(Long id, Long userId) {
//        filmDao.addLike(id, userId);
//        eventDao.create(new Event(null, null, userId, Event.EventType.LIKE, Event.Operation.ADD, id));
    }

    @Override
    @Transactional
    public void deleteLike(Long id, Long userId) {
//        if (userDao.existsById(userId)) {
//            filmDao.deleteLike(id, userId);
//            eventDao.create(new Event(null, null, userId, Event.EventType.LIKE, Event.Operation.REMOVE, id));
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
//        }
    }

    @Override
    public List<Film> findCommon(Long userId, Long friendId) {
//        return filmDao.findCommon(userId, friendId);
        return null;
    }

    @Override
    public List<Film> findPopular(Integer count, Optional<Long> genreId, Optional<Integer> year) {
//        return filmDao.findPopular(count, genreId, year);
        return null;
    }

    @Override
    public List<Film> search(String query, List<String> by) {
//        return filmDao.search(query, by);
        return null;
    }
}

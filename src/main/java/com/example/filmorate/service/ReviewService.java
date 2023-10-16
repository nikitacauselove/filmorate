package com.example.filmorate.service;

import com.example.filmorate.dao.EventDao;
import com.example.filmorate.dao.FilmDao;
import com.example.filmorate.dao.ReviewDao;
import com.example.filmorate.dao.UserDao;
import com.example.filmorate.model.Event;
import com.example.filmorate.model.Review;
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
public class ReviewService {
    private final EventDao eventDao;
    private final FilmDao filmDao;
    private final ReviewDao reviewDao;
    private final UserDao userDao;

    @Transactional
    public Review create(Review review) {
        if (!userDao.existsById(review.getUserId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
        if (!filmDao.existsById(review.getFilmId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Фильм с указанным идентификатором не найден.");
        }
        reviewDao.create(review);
        eventDao.create(new Event(null, null, review.getUserId(), Event.EventType.REVIEW, Event.Operation.ADD, review.getId()));

        return reviewDao.findById(review.getId());
    }

    @Transactional
    public Review update(Review review) {
        reviewDao.update(review);
        eventDao.create(new Event(null, null, review.getUserId(), Event.EventType.REVIEW, Event.Operation.UPDATE, review.getId()));

        return reviewDao.findById(review.getId());
    }

    public Review findById(int id) {
        return reviewDao.findById(id);
    }

    public List<Review> findAll(Optional<Integer> filmId, int count) {
        return reviewDao.findAll(filmId, count);
    }

    @Transactional
    public void deleteById(int id) {
        Review review = findById(id);

        reviewDao.deleteById(id);
        eventDao.create(new Event(null, null, review.getUserId(), Event.EventType.REVIEW, Event.Operation.REMOVE, review.getId()));
    }

    @Transactional
    public void addMark(int id, int userId, Review.MarkType markType) {
        reviewDao.addMark(id, userId, markType);
    }

    @Transactional
    public void deleteMark(int id, int userId, Review.MarkType markType) {
        reviewDao.deleteMark(id, userId, markType);
    }
}

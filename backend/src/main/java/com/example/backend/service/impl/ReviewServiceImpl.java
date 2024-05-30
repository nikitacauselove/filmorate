package com.example.backend.service.impl;

import com.example.backend.dao.EventDao;
import com.example.backend.dao.FilmDao;
import com.example.backend.dao.ReviewDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.Event;
import com.example.backend.entity.Review;
import com.example.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final EventDao eventDao;
    private final FilmDao filmDao;
    private final ReviewDao reviewDao;
    private final UserDao userDao;

    @Override
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

    @Override
    @Transactional
    public Review update(Review review) {
        reviewDao.update(review);
        eventDao.create(new Event(null, null, review.getUserId(), Event.EventType.REVIEW, Event.Operation.UPDATE, review.getId()));

        return reviewDao.findById(review.getId());
    }

    @Override
    public Review findById(Long id) {
        return reviewDao.findById(id);
    }

    @Override
    public List<Review> findAll(Optional<Long> filmId, Integer count) {
        return reviewDao.findAll(filmId, count);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Review review = findById(id);

        reviewDao.deleteById(id);
        eventDao.create(new Event(null, null, review.getUserId(), Event.EventType.REVIEW, Event.Operation.REMOVE, review.getId()));
    }

    @Override
    @Transactional
    public void addMark(Long id, Long userId, Review.MarkType markType) {
        reviewDao.addMark(id, userId, markType);
    }

    @Override
    @Transactional
    public void deleteMark(Long id, Long userId, Review.MarkType markType) {
        reviewDao.deleteMark(id, userId, markType);
    }
}

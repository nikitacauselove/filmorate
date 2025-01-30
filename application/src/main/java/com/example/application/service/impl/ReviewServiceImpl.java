package com.example.application.service.impl;

import com.example.api.model.type.EventType;
import com.example.api.model.type.MarkType;
import com.example.api.model.type.Operation;
import com.example.application.domain.Review;
import com.example.application.persistence.EventPersistenceService;
import com.example.application.persistence.ReviewPersistenceService;
import com.example.application.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final EventPersistenceService eventPersistenceService;
    private final ReviewPersistenceService reviewPersistenceService;

    @Override
    @Transactional
    public Review create(Review review) {
        return reviewPersistenceService.create(review);
    }

    @Override
    public Review update(Review review) {
        return reviewPersistenceService.update(review);
    }

    @Override
    public Review findById(Long id) {
        return reviewPersistenceService.findById(id);
    }

    @Override
    public List<Review> findAll(Long filmId, Integer count) {
        return reviewPersistenceService.findAll(filmId, count);
    }

    @Override
    public void deleteById(Long id) {
        reviewPersistenceService.deleteById(id);
    }

    @Override
    public void addMark(Long id, Long userId, MarkType markType) {
        reviewPersistenceService.addMark(id, userId, markType);
    }

    @Override
    public void deleteMark(Long id, Long userId, MarkType markType) {
        reviewPersistenceService.deleteMark(id, userId, markType);
    }
}

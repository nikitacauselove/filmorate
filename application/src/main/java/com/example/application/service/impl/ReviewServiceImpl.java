package com.example.application.service.impl;

import com.example.application.domain.Event;
import com.example.application.domain.EventType;
import com.example.application.domain.MarkType;
import com.example.application.domain.Operation;
import com.example.application.domain.Review;
import com.example.application.persistence.EventPersistenceService;
import com.example.application.persistence.ReviewPersistenceService;
import com.example.application.persistence.UserPersistenceService;
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
    private final UserPersistenceService userPersistenceService;

    @Override
    @Transactional
    public Review create(Review review) {
        Review createdReview = reviewPersistenceService.create(review);

        eventPersistenceService.create(Event.builder()
                .user(userPersistenceService.findById(createdReview.userId()))
                .eventType(EventType.REVIEW)
                .operation(Operation.ADD)
                .entityId(createdReview.id())
                .build());
        return createdReview;
    }

    @Override
    public Review update(Review review) {
        Review updatedReview = reviewPersistenceService.update(review);

        eventPersistenceService.create(Event.builder()
                .user(userPersistenceService.findById(updatedReview.userId()))
                .eventType(EventType.REVIEW)
                .operation(Operation.UPDATE)
                .entityId(updatedReview.id())
                .build());
        return updatedReview;
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
    @Transactional
    public void deleteById(Long id) {
        Review review = reviewPersistenceService.findById(id);

        reviewPersistenceService.deleteById(id);
        eventPersistenceService.create(Event.builder()
                .user(userPersistenceService.findById(review.userId()))
                .eventType(EventType.REVIEW)
                .operation(Operation.REMOVE)
                .entityId(review.id())
                .build());
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

package com.example.application.service.impl;

import com.example.api.model.ReviewDto;
import com.example.application.entity.Event;
import com.example.application.entity.EventType;
import com.example.application.entity.MarkType;
import com.example.application.entity.Operation;
import com.example.application.entity.Review;
import com.example.application.entity.ReviewMark;
import com.example.application.entity.ReviewMarkId;
import com.example.application.exception.NotFoundException;
import com.example.application.mapper.ReviewMapper;
import com.example.application.repository.EventRepository;
import com.example.application.repository.FilmRepository;
import com.example.application.repository.ReviewMarkRepository;
import com.example.application.repository.ReviewRepository;
import com.example.application.repository.UserRepository;
import com.example.application.repository.specification.ReviewSpecification;
import com.example.application.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Sort SORT_BY_DESCENDING_USEFUL = Sort.by(Sort.Direction.DESC, Review.Fields.useful);

    private final EventRepository eventRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewMarkRepository reviewMarkRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewSpecification reviewSpecification;

    @Override
    @Transactional
    public Review create(Review review) {
        if (!userRepository.existsById(review.getUserId())) {
            throw new NotFoundException(UserRepository.NOT_FOUND);
        }
        if (!filmRepository.existsById(review.getFilmId())) {
            throw new NotFoundException(FilmRepository.NOT_FOUND);
        }
        reviewRepository.save(review);
        eventRepository.save(Event.builder()
                .userId(review.getUserId())
                .eventType(EventType.REVIEW)
                .operation(Operation.ADD)
                .entityId(review.getId())
                .build());
        return review;
    }

    @Override
    @Transactional
    public Review update(ReviewDto reviewDto) {
        Review review = findById(reviewDto.reviewId());

        reviewMapper.updateEntity(reviewDto, review);
        eventRepository.save(Event.builder()
                .userId(review.getUserId())
                .eventType(EventType.REVIEW)
                .operation(Operation.UPDATE)
                .entityId(review.getId())
                .build());
        return review;
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ReviewRepository.NOT_FOUND));
    }

    @Override
    public List<Review> findAll(Long filmId, Integer count) {
        Specification<Review> specification = reviewSpecification.byFilmId(filmId);
        Pageable pageable = PageRequest.of(0, count, SORT_BY_DESCENDING_USEFUL);

        return reviewRepository.findAll(specification, pageable).getContent();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Review review = findById(id);

        reviewRepository.deleteById(id);
        eventRepository.save(Event.builder()
                .userId(review.getUserId())
                .eventType(EventType.REVIEW)
                .operation(Operation.REMOVE)
                .entityId(id)
                .build());
    }

    @Override
    @Transactional
    public void addMark(Long id, Long userId, MarkType markType) {
        Review review = findById(id);
        ReviewMarkId reviewMarkId = ReviewMarkId.builder()
                .reviewId(id)
                .userId(userId)
                .build();

        switch (markType) {
            case DISLIKE -> review.setUseful(review.getUseful() - 1);
            case LIKE -> review.setUseful(review.getUseful() + 1);
        }
        reviewMarkRepository.save(ReviewMark.builder()
                .id(reviewMarkId)
                .markType(markType)
                .build());
    }

    @Override
    @Transactional
    public void deleteMark(Long id, Long userId, MarkType markType) {
        Review review = findById(id);
        ReviewMarkId reviewMarkId = ReviewMarkId.builder()
                .reviewId(id)
                .userId(userId)
                .build();

        switch (markType) {
            case DISLIKE -> review.setUseful(review.getUseful() + 1);
            case LIKE -> review.setUseful(review.getUseful() - 1);
        }
        reviewMarkRepository.deleteById(reviewMarkId);
    }
}

package com.example.backend.service.impl;

import com.example.api.dto.ReviewDto;
import com.example.backend.mapper.ReviewMapper;
import com.example.backend.repository.entity.Event;
import com.example.backend.repository.entity.Review;
import com.example.backend.repository.entity.ReviewMark;
import com.example.backend.repository.entity.ReviewMarkId;
import com.example.backend.repository.FilmRepository;
import com.example.backend.repository.ReviewMarkRepository;
import com.example.backend.repository.ReviewRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.EventService;
import com.example.backend.service.ReviewService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMarkRepository reviewMarkRepository;
    private final EventService eventService;

    @Override
    @Transactional
    public Review create(ReviewDto reviewDto) {
        if (!userRepository.existsById(reviewDto.userId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
        if (!filmRepository.existsById(reviewDto.filmId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Фильм с указанным идентификатором не найден.");
        }
        Review review = reviewMapper.toReview(reviewDto);

        eventService.create(review.getUserId(), Event.EventType.REVIEW, Event.Operation.ADD, review.getId());
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Review update(ReviewDto reviewDto) {
        Review review = findById(reviewDto.reviewId());

        eventService.create(review.getUserId(), Event.EventType.REVIEW, Event.Operation.UPDATE, review.getId());
        return reviewMapper.updateReview(reviewDto, review);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Отзыв с указанным идентификатором не найден."));
    }

    @Override
    public List<Review> findAll(Long filmId, Integer count) {
        return reviewRepository.findAll(createFindAllSpecification(filmId), PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "useful"))).getContent();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Review review = findById(id);

        eventService.create(review.getUserId(), Event.EventType.REVIEW, Event.Operation.REMOVE, review.getId());
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addMark(Long id, Long userId, Review.MarkType markType) {
        Review review = findById(id);
        ReviewMarkId reviewMarkId = new ReviewMarkId(id, userId);

        if (markType == Review.MarkType.LIKE) {
            review.setUseful(review.getUseful() + 1);
        } else {
            review.setUseful(review.getUseful() - 1);
        }

        reviewMarkRepository.save(new ReviewMark(reviewMarkId, markType));
    }

    @Override
    @Transactional
    public void deleteMark(Long id, Long userId, Review.MarkType markType) {
        Review review = findById(id);
        ReviewMarkId reviewMarkId = new ReviewMarkId(id, userId);

        if (markType == Review.MarkType.LIKE) {
            review.setUseful(review.getUseful() - 1);
        } else {
            review.setUseful(review.getUseful() + 1);
        }

        reviewMarkRepository.deleteById(reviewMarkId);
    }

    private Specification<Review> createFindAllSpecification(Long filmId) {
        return ((root, query1, criteriaBuilder) -> {
            Collection<Predicate> predicates = new ArrayList<>();

            if (filmId != null) {
                predicates.add(criteriaBuilder.equal(root.get("filmId"), filmId));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}

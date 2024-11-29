package com.example.application.service.impl;

import com.example.api.dto.enums.EventType;
import com.example.api.dto.enums.Operation;
import com.example.api.dto.enums.MarkType;
import com.example.application.repository.entity.Review;
import com.example.application.repository.entity.ReviewMark;
import com.example.application.repository.entity.ReviewMarkId;
import com.example.application.repository.FilmRepository;
import com.example.application.repository.ReviewMarkRepository;
import com.example.application.repository.ReviewRepository;
import com.example.application.repository.UserRepository;
import com.example.application.service.EventService;
import com.example.application.service.ReviewService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final EventService eventService;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final ReviewMarkRepository reviewMarkRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review create(Review review) {
        if (!userRepository.existsById(review.getUserId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден");
        }
        if (!filmRepository.existsById(review.getFilmId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Фильм с указанным идентификатором не найден");
        }
        reviewRepository.save(review);
        eventService.create(review.getUserId(), EventType.REVIEW, Operation.ADD, review.getId());
        return review;
    }

    @Override
    @Transactional
    public Review update(Review review) {
        eventService.create(review.getUserId(), EventType.REVIEW, Operation.UPDATE, review.getId());
        return reviewRepository.save(review);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Отзыв с указанным идентификатором не найден"));
    }

    @Override
    public List<Review> findAll(Long filmId, Integer count) {
        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "useful"));

        return reviewRepository.findAll(createFindAllSpecification(filmId), pageable).getContent();
    }

    @Override
    @Transactional
    public void deleteById(Long id, Long userId) {
        eventService.create(userId, EventType.REVIEW, Operation.REMOVE, id);
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addMark(Long id, Long userId, MarkType markType) {
        Review review = findById(id);
        ReviewMarkId reviewMarkId = ReviewMarkId.builder()
                .reviewId(id)
                .userId(userId)
                .build();

        if (markType == MarkType.LIKE) {
            review.setUseful(review.getUseful() + 1);
        } else {
            review.setUseful(review.getUseful() - 1);
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

        if (markType == MarkType.LIKE) {
            review.setUseful(review.getUseful() - 1);
        } else {
            review.setUseful(review.getUseful() + 1);
        }
        reviewMarkRepository.deleteById(reviewMarkId);
    }

    private Specification<Review> createFindAllSpecification(Long filmId) {
        return ((root, query1, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filmId != null) {
                predicates.add(criteriaBuilder.equal(root.get("filmId"), filmId));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        });
    }
}

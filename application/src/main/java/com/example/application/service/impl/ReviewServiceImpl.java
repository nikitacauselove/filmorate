package com.example.application.service.impl;

import com.example.api.dto.ReviewDto;
import com.example.api.dto.enums.EventType;
import com.example.api.dto.enums.Operation;
import com.example.api.dto.enums.MarkType;
import com.example.application.mapper.ReviewMapper;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден");
        }
        if (!filmRepository.existsById(reviewDto.filmId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Фильм с указанным идентификатором не найден");
        }
        Review review = reviewMapper.toReview(reviewDto);

        reviewRepository.save(review);
        eventService.create(review.getUserId(), EventType.REVIEW, Operation.ADD, review.getId());
        return review;
    }

    @Override
    @Transactional
    public Review update(ReviewDto reviewDto) {
        Review review = findById(reviewDto.reviewId());

        eventService.create(review.getUserId(), EventType.REVIEW, Operation.UPDATE, review.getId());
        return reviewMapper.updateReview(reviewDto, review);
    }

    @Override
    @Transactional(readOnly = true)
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Отзыв с указанным идентификатором не найден"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> findAll(Long filmId, Integer count) {
        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "useful"));

        return reviewRepository.findAll(createFindAllSpecification(filmId), pageable).getContent();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Review review = findById(id);

        eventService.create(review.getUserId(), EventType.REVIEW, Operation.REMOVE, review.getId());
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
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}

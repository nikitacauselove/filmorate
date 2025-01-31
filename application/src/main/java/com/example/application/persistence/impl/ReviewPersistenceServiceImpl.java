package com.example.application.persistence.impl;

import com.example.application.domain.EventType;
import com.example.application.domain.MarkType;
import com.example.application.domain.Operation;
import com.example.application.domain.Review;
import com.example.application.persistence.EventPersistenceService;
import com.example.application.persistence.ReviewPersistenceService;
import com.example.application.persistence.UserPersistenceService;
import com.example.application.persistence.mapper.ReviewEntityMapper;
import com.example.application.persistence.model.ReviewEntity;
import com.example.application.persistence.model.ReviewMarkEntity;
import com.example.application.persistence.model.ReviewMarkEntityId;
import com.example.application.persistence.repository.FilmRepository;
import com.example.application.persistence.repository.ReviewMarkRepository;
import com.example.application.persistence.repository.ReviewRepository;
import com.example.application.persistence.repository.UserRepository;
import com.example.application.persistence.specification.ReviewSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ReviewPersistenceServiceImpl implements ReviewPersistenceService {

    private static final Sort SORT_BY_DESCENDING_USEFUL = Sort.by(Sort.Direction.DESC, ReviewEntity.Fields.useful);

    private final EventPersistenceService eventPersistenceService;
    private final FilmRepository filmRepository;
    private final UserPersistenceService userPersistenceService;
    private final ReviewEntityMapper reviewEntityMapper;
    private final ReviewMarkRepository reviewMarkRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewSpecification reviewSpecification;

    @Override
    public Review create(Review review) {
        ReviewEntity reviewEntity = reviewEntityMapper.toEntity(review);

        if (!userPersistenceService.existsById(review.userId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, UserRepository.NOT_FOUND);
        }
        if (!filmRepository.existsById(review.filmId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, FilmRepository.NOT_FOUND);
        }
        ReviewEntity savedEntity = reviewRepository.save(reviewEntity);
        eventPersistenceService.create(savedEntity.getUserId(), EventType.REVIEW, Operation.ADD, savedEntity.getId());

        return reviewEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Review update(Review review) {
        ReviewEntity reviewEntity = reviewRepository.findById(review.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ReviewRepository.NOT_FOUND));
        ReviewEntity updatedEntity = reviewEntityMapper.updateEntity(review, reviewEntity);

        eventPersistenceService.create(updatedEntity.getUserId(), EventType.REVIEW, Operation.UPDATE, updatedEntity.getId());
        return reviewEntityMapper.toDomain(updatedEntity);
    }

    @Override
    public Review findById(Long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ReviewRepository.NOT_FOUND));

        return reviewEntityMapper.toDomain(reviewEntity);
    }

    @Override
    public List<Review> findAll(Long filmId, Integer count) {
        Specification<ReviewEntity> specification = reviewSpecification.findAll(filmId);
        Pageable pageable = PageRequest.of(0, count, SORT_BY_DESCENDING_USEFUL);
        List<ReviewEntity> reviewEntityList = reviewRepository.findAll(specification, pageable).getContent();

        return reviewEntityMapper.toDomain(reviewEntityList);
    }

    @Override
    public void deleteById(Long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ReviewRepository.NOT_FOUND));

        reviewRepository.deleteById(id);
        eventPersistenceService.create(reviewEntity.getUserId(), EventType.REVIEW, Operation.REMOVE, id);
    }

    @Override
    public void addMark(Long id, Long userId, MarkType markType) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ReviewRepository.NOT_FOUND));
        ReviewMarkEntityId reviewMarkId = ReviewMarkEntityId.builder()
                .reviewId(id)
                .userId(userId)
                .build();

        switch (markType) {
            case DISLIKE -> reviewEntity.setUseful(reviewEntity.getUseful() - 1);
            case LIKE -> reviewEntity.setUseful(reviewEntity.getUseful() + 1);
        }
        reviewMarkRepository.save(ReviewMarkEntity.builder()
                .id(reviewMarkId)
                .markType(markType)
                .build());
    }

    @Override
    public void deleteMark(Long id, Long userId, MarkType markType) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ReviewRepository.NOT_FOUND));
        ReviewMarkEntityId reviewMarkId = ReviewMarkEntityId.builder()
                .reviewId(id)
                .userId(userId)
                .build();

        switch (markType) {
            case DISLIKE -> reviewEntity.setUseful(reviewEntity.getUseful() + 1);
            case LIKE -> reviewEntity.setUseful(reviewEntity.getUseful() - 1);
        }
        reviewMarkRepository.deleteById(reviewMarkId);
    }
}

package com.example.application.persistence.impl;

import com.example.application.domain.MarkType;
import com.example.application.domain.Review;
import com.example.application.exception.NotFoundException;
import com.example.application.persistence.ReviewPersistenceService;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewPersistenceServiceImpl implements ReviewPersistenceService {

    private static final Sort SORT_BY_DESCENDING_USEFUL = Sort.by(Sort.Direction.DESC, ReviewEntity.Fields.useful);

    private final FilmRepository filmRepository;
    private final ReviewEntityMapper reviewEntityMapper;
    private final ReviewMarkRepository reviewMarkRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewSpecification reviewSpecification;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Review create(Review review) {
        if (!userRepository.existsById(review.userId())) {
            throw new NotFoundException(UserRepository.NOT_FOUND);
        }
        if (!filmRepository.existsById(review.filmId())) {
            throw new NotFoundException(FilmRepository.NOT_FOUND);
        }
        return reviewEntityMapper.toDomain(reviewRepository.save(reviewEntityMapper.toEntity(review)));
    }

    @Override
    @Transactional
    public Review update(Review review) {
        ReviewEntity reviewEntity = reviewRepository.findById(review.id())
                .orElseThrow(() -> new NotFoundException(ReviewRepository.NOT_FOUND));

        return reviewEntityMapper.toDomain(reviewEntityMapper.updateEntity(review, reviewEntity));
    }

    @Override
    public Review findById(Long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ReviewRepository.NOT_FOUND));

        return reviewEntityMapper.toDomain(reviewEntity);
    }

    @Override
    public List<Review> findAll(Long filmId, Integer count) {
        Specification<ReviewEntity> specification = reviewSpecification.byFilmId(filmId);
        Pageable pageable = PageRequest.of(0, count, SORT_BY_DESCENDING_USEFUL);

        return reviewEntityMapper.toDomain(reviewRepository.findAll(specification, pageable).getContent());
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addMark(Long id, Long userId, MarkType markType) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ReviewRepository.NOT_FOUND));
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
    @Transactional
    public void deleteMark(Long id, Long userId, MarkType markType) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ReviewRepository.NOT_FOUND));
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

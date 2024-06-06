package com.example.backend.web;

import com.example.api.ReviewApi;
import com.example.api.dto.ReviewDto;
import com.example.backend.repository.entity.Review;
import com.example.backend.mapper.ReviewMapper;
import com.example.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto create(ReviewDto reviewDto) {
        Review review = reviewMapper.mapToReview(reviewDto);

        return reviewMapper.mapToReviewDto(reviewService.create(review));
    }

    public ReviewDto update(ReviewDto reviewDto) {
        Review review = reviewMapper.mapToReview(reviewDto, reviewService.findById(reviewDto.reviewId()));

        return reviewMapper.mapToReviewDto(reviewService.update(review));
    }

    public ReviewDto findById(Long id) {
        return reviewMapper.mapToReviewDto(reviewService.findById(id));
    }

    public List<ReviewDto> findAll(Optional<Long> filmId, Integer count) {
        return reviewMapper.mapToReviewDto(reviewService.findAll(filmId, count));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        reviewService.deleteById(id);
    }

    public void addLike(Long id, Long userId) {
        reviewService.addMark(id, userId, Review.MarkType.LIKE);
    }

    public void deleteLike(Long id, Long userId) {
        reviewService.deleteMark(id, userId, Review.MarkType.LIKE);
    }

    public void addDislike(Long id, Long userId) {
        reviewService.addMark(id, userId, Review.MarkType.DISLIKE);
    }

    public void deleteDislike(Long id, Long userId) {
        reviewService.deleteMark(id, userId, Review.MarkType.DISLIKE);
    }
}

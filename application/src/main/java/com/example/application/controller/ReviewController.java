package com.example.application.controller;

import com.example.api.ReviewApi;
import com.example.api.model.ReviewDto;
import com.example.api.model.type.MarkType;
import com.example.application.controller.mapper.ReviewDtoMapper;
import com.example.application.domain.Review;
import com.example.application.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

    private final ReviewDtoMapper reviewDtoMapper;
    private final ReviewService reviewService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto create(ReviewDto reviewDto) {
        Review review = reviewDtoMapper.toDomain(reviewDto);

        return reviewDtoMapper.toDto(reviewService.create(review));
    }

    @Override
    public ReviewDto update(ReviewDto reviewDto) {
        Review review = reviewDtoMapper.toDomain(reviewDto);

        return reviewDtoMapper.toDto(reviewService.update(review));
    }

    @Override
    public ReviewDto findById(Long id) {
        return reviewDtoMapper.toDto(reviewService.findById(id));
    }

    @Override
    public List<ReviewDto> findAll(Long filmId, Integer count) {
        return reviewDtoMapper.toDto(reviewService.findAll(filmId, count));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        reviewService.deleteById(id);
    }

    @Override
    public void addLike(Long id, Long userId) {
        reviewService.addMark(id, userId, MarkType.LIKE);
    }

    @Override
    public void deleteLike(Long id, Long userId) {
        reviewService.deleteMark(id, userId, MarkType.LIKE);
    }

    @Override
    public void addDislike(Long id, Long userId) {
        reviewService.addMark(id, userId, MarkType.DISLIKE);
    }

    @Override
    public void deleteDislike(Long id, Long userId) {
        reviewService.deleteMark(id, userId, MarkType.DISLIKE);
    }
}

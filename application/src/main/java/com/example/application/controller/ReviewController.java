package com.example.application.controller;

import com.example.api.ReviewApi;
import com.example.api.dto.ReviewDto;
import com.example.api.dto.enums.MarkType;
import com.example.application.mapper.ReviewMapper;
import com.example.application.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto create(ReviewDto reviewDto) {
        return reviewMapper.toReviewDto(reviewService.create(reviewDto));
    }

    @Override
    public ReviewDto update(ReviewDto reviewDto) {
        return reviewMapper.toReviewDto(reviewService.update(reviewDto));
    }

    @Override
    public ReviewDto findById(Long id) {
        return reviewMapper.toReviewDto(reviewService.findById(id));
    }

    @Override
    public List<ReviewDto> findAll(Long filmId, Integer count) {
        return reviewMapper.toReviewDto(reviewService.findAll(filmId, count));
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

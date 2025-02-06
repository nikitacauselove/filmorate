package com.example.application.controller;

import com.example.api.ReviewApi;
import com.example.api.model.ReviewDto;
import com.example.application.entity.MarkType;
import com.example.application.entity.Review;
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
        Review review = reviewMapper.toEntity(reviewDto);

        return reviewMapper.toDto(reviewService.create(review));
    }

    @Override
    public ReviewDto update(ReviewDto reviewDto) {
        return reviewMapper.toDto(reviewService.update(reviewDto));
    }

    @Override
    public ReviewDto findById(Long id) {
        return reviewMapper.toDto(reviewService.findById(id));
    }

    @Override
    public List<ReviewDto> findAll(Long filmId, Integer count) {
        return reviewMapper.toDto(reviewService.findAll(filmId, count));
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

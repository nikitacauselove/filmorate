package com.example.application.service;

import com.example.api.dto.ReviewDto;
import com.example.api.dto.enums.MarkType;
import com.example.application.repository.entity.Review;

import java.util.List;

public interface ReviewService {

    Review create(ReviewDto reviewDto);

    Review update(ReviewDto reviewDto);

    Review findById(Long id);

    List<Review> findAll(Long filmId, Integer count);

    void deleteById(Long id);

    void addMark(Long id, Long userId, MarkType markType);

    void deleteMark(Long id, Long userId, MarkType markType);
}

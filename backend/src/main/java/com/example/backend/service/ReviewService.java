package com.example.backend.service;

import com.example.backend.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Review create(Review review);

    Review update(Review review);

    Review findById(Long id);

    List<Review> findAll(Optional<Long> filmId, Integer count);

    void deleteById(Long id);

    void addMark(Long id, Long userId, Review.MarkType markType);

    void deleteMark(Long id, Long userId, Review.MarkType markType);
}

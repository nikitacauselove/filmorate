package com.example.backend.service;

import com.example.backend.repository.entity.Review;

import java.util.List;

public interface ReviewService {

    Review create(Review review);

    Review update(Review review);

    Review findById(Long id);

    List<Review> findAll(Long filmId, Integer count);

    void deleteById(Long id);

    void addMark(Long id, Long userId, Review.MarkType markType);

    void deleteMark(Long id, Long userId, Review.MarkType markType);
}

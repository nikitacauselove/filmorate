package com.example.backend.dao;

import com.example.backend.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewDao {

    void create(Review review);

    void update(Review review);

    Review findById(int id);

    List<Review> findAll(Optional<Integer> filmId, int count);

    void deleteById(int id);

    void addMark(int id, int userId, Review.MarkType markType);

    void deleteMark(int id, int userId, Review.MarkType markType);
}

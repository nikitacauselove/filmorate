package com.filmorate.filmorate.model.mapper;

import com.filmorate.filmorate.model.Review;
import com.filmorate.filmorate.model.dto.ReviewDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewMapper {
    public static Review toReview(ReviewDto reviewDto) {
        return new Review(reviewDto.getReviewId(), reviewDto.getContent(), reviewDto.getIsPositive(), reviewDto.getUserId(), reviewDto.getFilmId(), 0);
    }

    public static Review toReview(ReviewDto reviewDto, Review review) {
        return new Review(review.getId(), reviewDto.getContent(), reviewDto.getIsPositive(), review.getUserId(), review.getFilmId(), review.getUseful());
    }

    public static ReviewDto toReviewDto(Review review) {
        return new ReviewDto(review.getId(), review.getContent(), review.getIsPositive(), review.getUserId(), review.getFilmId(), review.getUseful());
    }

    public static List<ReviewDto> toReviewDto(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewMapper::toReviewDto)
                .collect(Collectors.toList());
    }
}

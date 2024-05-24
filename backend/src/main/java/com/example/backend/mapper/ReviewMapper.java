package com.example.backend.mapper;

import com.example.api.dto.ReviewDto;
import com.example.backend.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ReviewMapper {

    public Review mapToReview(ReviewDto reviewDto) {
        return new Review(reviewDto.reviewId(), reviewDto.content(), reviewDto.isPositive(), reviewDto.userId(), reviewDto.filmId(), 0);
    }

    public Review mapToReview(ReviewDto reviewDto, Review review) {
        return new Review(review.getId(), reviewDto.content(), reviewDto.isPositive(), review.getUserId(), review.getFilmId(), review.getUseful());
    }

    public ReviewDto mapToReviewDto(Review review) {
        return new ReviewDto(review.getId(), review.getContent(), review.getIsPositive(), review.getUserId(), review.getFilmId(), review.getUseful());
    }

    public abstract List<ReviewDto> mapToReviewDto(List<Review> reviews);
}

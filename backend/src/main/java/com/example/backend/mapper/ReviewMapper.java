package com.example.backend.mapper;

import com.example.api.dto.ReviewDto;
import com.example.backend.repository.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ReviewMapper {

    @Mapping(target = "id", source = "reviewId")
    @Mapping(target = "useful", defaultValue = "0")
    public abstract Review mapToReview(ReviewDto reviewDto);

    public Review updateReview(ReviewDto reviewDto, Review review) {
        return new Review(review.getId(), reviewDto.content(), reviewDto.isPositive(), review.getUserId(), review.getFilmId(), review.getUseful());
    }

    @Mapping(target = "reviewId", source = "id")
    public abstract ReviewDto mapToReviewDto(Review review);

    public abstract List<ReviewDto> mapToReviewDto(List<Review> reviewList);
}

package com.example.backend.mapper;

import com.example.api.dto.ReviewDto;
import com.example.backend.repository.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "id", source = "reviewId")
    @Mapping(target = "useful", constant = "0")
    Review toReview(ReviewDto reviewDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "filmId", ignore = true)
    @Mapping(target = "useful", ignore = true)
    Review updateReview(ReviewDto reviewDto, @MappingTarget Review review);

    @Mapping(target = "reviewId", source = "id")
    ReviewDto toReviewDto(Review review);

    List<ReviewDto> toReviewDto(List<Review> reviewList);
}

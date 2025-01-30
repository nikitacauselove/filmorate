package com.example.application.controller.mapper;

import com.example.api.model.ReviewDto;
import com.example.application.domain.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewDtoMapper {

    @Mapping(target = "id", source = "reviewId")
    @Mapping(target = "useful", constant = "0")
    Review toDomain(ReviewDto reviewDto);

    @Mapping(target = "reviewId", source = "id")
    ReviewDto toDto(Review review);

    List<ReviewDto> toDto(List<Review> reviewList);
}

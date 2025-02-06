package com.example.application.mapper;

import com.example.api.model.ReviewDto;
import com.example.application.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "id", source = "reviewId")
    @Mapping(target = "useful", constant = "0")
    Review toEntity(ReviewDto reviewDto);

    @Mapping(target = "id", source = "reviewId")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "filmId", ignore = true)
    @Mapping(target = "useful", ignore = true)
    Review updateEntity(ReviewDto reviewDto, @MappingTarget Review review);

    @Mapping(target = "reviewId", source = "id")
    ReviewDto toDto(Review review);

    List<ReviewDto> toDto(List<Review> reviewList);
}

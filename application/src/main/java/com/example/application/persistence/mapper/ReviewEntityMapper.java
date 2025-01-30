package com.example.application.persistence.mapper;

import com.example.application.domain.Review;
import com.example.application.persistence.model.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewEntityMapper {

    @Mapping(target = "id", ignore = true)
    ReviewEntity toEntity(Review review);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "filmId", ignore = true)
    @Mapping(target = "useful", ignore = true)
    ReviewEntity updateEntity(Review review, @MappingTarget ReviewEntity reviewEntity);

    Review toDomain(ReviewEntity reviewEntity);

    List<Review> toDomain(List<ReviewEntity> reviewEntityList);
}

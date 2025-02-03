package com.example.application.persistence.specification;

import com.example.application.persistence.model.ReviewEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ReviewSpecification {

    public Specification<ReviewEntity> byFilmId(Long filmId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (filmId != null) {
                return criteriaBuilder.equal(root.get(ReviewEntity.Fields.filmId), filmId);
            }
            return criteriaBuilder.conjunction();
        };
    }
}

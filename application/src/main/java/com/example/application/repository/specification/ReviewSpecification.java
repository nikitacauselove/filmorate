package com.example.application.repository.specification;

import com.example.application.entity.Review;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ReviewSpecification {

    public Specification<Review> byFilmId(Long filmId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (filmId != null) {
                return criteriaBuilder.equal(root.get(Review.Fields.filmId), filmId);
            }
            return criteriaBuilder.conjunction();
        };
    }
}

package com.example.application.repository.specification;

import com.example.application.repository.entity.Review;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewSpecification {

    public Specification<Review> findAll(Long filmId) {
        return ((root, query1, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filmId != null) {
                predicates.add(criteriaBuilder.equal(root.get("filmId"), filmId));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        });
    }
}

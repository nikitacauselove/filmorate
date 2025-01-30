package com.example.application.persistence.repository;

import com.example.application.persistence.model.ReviewMarkEntity;
import com.example.application.persistence.model.ReviewMarkEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для взаимодействия с оценками рецензий.
 */
@Repository
public interface ReviewMarkRepository extends JpaRepository<ReviewMarkEntity, ReviewMarkEntityId> {
}

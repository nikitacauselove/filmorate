package com.example.application.repository;

import com.example.application.entity.ReviewMark;
import com.example.application.entity.ReviewMarkId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для взаимодействия с оценками рецензий.
 */
public interface ReviewMarkRepository extends JpaRepository<ReviewMark, ReviewMarkId> {
}

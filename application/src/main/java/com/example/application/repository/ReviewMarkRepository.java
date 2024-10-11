package com.example.application.repository;

import com.example.application.repository.entity.ReviewMark;
import com.example.application.repository.entity.ReviewMarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для взаимодействия с оценками рецензий на фильмы.
 */
@Repository
public interface ReviewMarkRepository extends JpaRepository<ReviewMark, ReviewMarkId> {
}

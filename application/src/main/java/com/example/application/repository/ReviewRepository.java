package com.example.application.repository;

import com.example.application.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Репозиторий для взаимодействия с рецензиями.
 */
public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    String NOT_FOUND = "Рецензия с указанным идентификатором не найдена";
}

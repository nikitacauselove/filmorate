package com.example.application.persistence.repository;

import com.example.application.persistence.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для взаимодействия с рецензиями.
 */
@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>, JpaSpecificationExecutor<ReviewEntity> {

    String NOT_FOUND = "Отзыв с указанным идентификатором не найден";

}

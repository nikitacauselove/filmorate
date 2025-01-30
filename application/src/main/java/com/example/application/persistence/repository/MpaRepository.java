package com.example.application.persistence.repository;

import com.example.application.persistence.model.MpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для взаимодействия с рейтингами Американской киноассоциации.
 */
@Repository
public interface MpaRepository extends JpaRepository<MpaEntity, Long> {

    String NOT_FOUND = "Рейтинг Американской киноассоциации с указанным идентификатором не найден";

}

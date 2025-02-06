package com.example.application.repository;

import com.example.application.entity.Mpa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для взаимодействия с рейтингами Американской киноассоциации.
 */
public interface MpaRepository extends JpaRepository<Mpa, Long> {

    String NOT_FOUND = "Рейтинг Американской киноассоциации с указанным идентификатором не найден";
}

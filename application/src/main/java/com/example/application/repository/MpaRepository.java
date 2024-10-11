package com.example.application.repository;

import com.example.application.repository.entity.Mpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для взаимодействия с системой рейтингов Американской киноассоциации.
 */
@Repository
public interface MpaRepository extends JpaRepository<Mpa, Long> {
}

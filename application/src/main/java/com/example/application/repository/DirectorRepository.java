package com.example.application.repository;

import com.example.application.repository.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для взаимодействия с режиссёрами.
 */
@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    Optional<Director> findByNameContainingIgnoreCase(String name);
}

package com.example.application.repository;

import com.example.application.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для взаимодействия с режиссёрами.
 */
public interface DirectorRepository extends JpaRepository<Director, Long> {

    String NOT_FOUND = "Режиссёр с указанным идентификатором не найден";

    /**
     * Получение информации о режиссёре.
     *
     * @param name имя режиссёра
     */
    Optional<Director> findByNameContainingIgnoreCase(String name);
}

package com.example.application.persistence.repository;

import com.example.application.persistence.model.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для взаимодействия с режиссёрами.
 */
@Repository
public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {

    String NOT_FOUND = "Режиссёр с указанным идентификатором не найден";

    /**
     * Получение информации о режиссёре.
     *
     * @param name имя режиссёра
     */
    Optional<DirectorEntity> findByNameContainingIgnoreCase(String name);
}

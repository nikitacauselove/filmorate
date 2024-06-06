package com.example.backend.repository;

import com.example.backend.repository.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    Optional<Director> findByNameContainingIgnoreCase(String name);
}

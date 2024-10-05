package com.example.application.repository;

import com.example.application.repository.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    Optional<Director> findByNameContainingIgnoreCase(String name);
}

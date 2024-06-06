package com.example.backend.service;

import com.example.backend.repository.entity.Director;

import java.util.List;

public interface DirectorService {

    Director create(Director director);

    Director update(Director director);

    Director findById(Long id);

    List<Director> findAll();

    void deleteById(Long id);
}

package com.example.backend.service;

import com.example.api.dto.Director;

import java.util.List;

public interface DirectorService {

    Director create(Director director);

    Director update(Director director);

    Director findById(int id);

    List<Director> findAll();

    void deleteById(int id);
}

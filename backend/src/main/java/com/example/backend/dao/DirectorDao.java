package com.example.backend.dao;

import com.example.backend.entity.Director;

import java.util.List;

public interface DirectorDao {

    void create(Director director);

    void update(Director director);

    Director findById(Long id);

    boolean existsById(Long id);

    List<Director> findAll();

    void deleteById(Long id);
}

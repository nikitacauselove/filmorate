package com.example.backend.dao;

import com.example.api.dto.Director;

import java.util.List;

public interface DirectorDao {

    void create(Director director);

    void update(Director director);

    Director findById(int id);

    boolean existsById(int id);

    List<Director> findAll();

    void deleteById(int id);
}

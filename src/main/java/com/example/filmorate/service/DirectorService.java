package com.example.filmorate.service;

import com.example.filmorate.entity.Director;

import java.util.List;

public interface DirectorService {

    Director create(Director director);

    Director update(Director director);

    Director findById(int id);

    List<Director> findAll();

    void deleteById(int id);
}

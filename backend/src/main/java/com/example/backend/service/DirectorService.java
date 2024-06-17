package com.example.backend.service;

import com.example.api.dto.DirectorDto;
import com.example.backend.repository.entity.Director;

import java.util.List;

public interface DirectorService {

    Director create(DirectorDto director);

    Director update(DirectorDto director);

    Director findById(Long id);

    List<Director> findAll();

    void deleteById(Long id);
}
